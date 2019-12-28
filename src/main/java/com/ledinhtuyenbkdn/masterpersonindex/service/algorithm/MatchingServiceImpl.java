package com.ledinhtuyenbkdn.masterpersonindex.service.algorithm;

import com.ledinhtuyenbkdn.masterpersonindex.model.*;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.Field;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.LinkStatus;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.PersonStatus;
import com.ledinhtuyenbkdn.masterpersonindex.repository.FieldWeightRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.MasterPersonRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.PersonRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.ReviewLinkRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.ProvinceService;
import com.ledinhtuyenbkdn.masterpersonindex.service.SettingService;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.PersonDTO;
import com.ledinhtuyenbkdn.masterpersonindex.service.mapper.PersonMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MatchingServiceImpl implements MatchingService {

    private BlockingService blockingService;

    private FieldWeightRepository fieldWeightRepository;

    private PersonRepository personRepository;

    private MasterPersonRepository masterPersonRepository;

    private ReviewLinkRepository reviewLinkRepository;

    private PersonMapper personMapper;

    private SettingService settingService;

    private GenerateGlobalIdService generateGlobalIdService;

    private ProvinceService provinceService;

    public MatchingServiceImpl(BlockingService blockingService, FieldWeightRepository fieldWeightRepository, PersonRepository personRepository, MasterPersonRepository masterPersonRepository, ReviewLinkRepository reviewLinkRepository, PersonMapper personMapper, SettingService settingService, GenerateGlobalIdService generateGlobalIdService, ProvinceService provinceService) {
        this.blockingService = blockingService;
        this.fieldWeightRepository = fieldWeightRepository;
        this.personRepository = personRepository;
        this.masterPersonRepository = masterPersonRepository;
        this.reviewLinkRepository = reviewLinkRepository;
        this.personMapper = personMapper;
        this.settingService = settingService;
        this.generateGlobalIdService = generateGlobalIdService;
        this.provinceService = provinceService;
    }

    @Override
    public void match(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);

        if (personDTO.getProvince() != null) {
            Optional<Province> provinceOptional = provinceService.findOne(personDTO.getProvince().getId());
            if (provinceOptional.isPresent()) {
                person.setProvince(provinceOptional.get());
            }
        }

        if (person.getHealthInsuranceNumber() != null && !"".equals(person.getHealthInsuranceNumber())) {
            Optional<MasterPerson> masterPersonOptional = masterPersonRepository.findByHealthInsuranceNumber(person.getHealthInsuranceNumber());
            if (masterPersonOptional.isPresent()) {
                createFastMatchMasterPerson(person, masterPersonOptional.get());
                return;
            }
        }

        if (person.getIdentificationNumber() != null && !"".equals(person.getIdentificationNumber())) {
            Optional<MasterPerson> masterPersonOptional = masterPersonRepository.findByIdentificationNumber(person.getIdentificationNumber());
            if (masterPersonOptional.isPresent()) {
                createFastMatchMasterPerson(person, masterPersonOptional.get());
                return;
            }
        }


        long algorithmId = settingService.getSettingValue("ALGORITHM", Long.class);
        long manualMatchScore = settingService.getSettingValue("MANUAL_MATCH_SCORE", Long.class);
        long autoMatchScore = settingService.getSettingValue("AUTO_MATCH_SCORE", Long.class);

        AlgorithmInterface algorithm = AlgorithmFactory.getAlgorithm((int) algorithmId);
        List<FieldWeight> fieldWeights = fieldWeightRepository.findAll();

        List<MasterPerson> candidates = blockingService.getCandidates(person);
        List<MasterPerson> possibleMatchMasterPerson = new ArrayList<>();
        List<Double> possibleMatchScore = new ArrayList<>();

        for (MasterPerson masterPerson : candidates) {
            double score = calculateScore(person, masterPerson, algorithm, fieldWeights);
            if (score > manualMatchScore) {
                possibleMatchMasterPerson.add(masterPerson);
                possibleMatchScore.add(score);
            }
        }

        if (possibleMatchMasterPerson.isEmpty()) {
            createNewMasterPerson(person);
        } else if (possibleMatchMasterPerson.size() > 1) {
            createReviewLinks(person, possibleMatchMasterPerson, possibleMatchScore);
        } else {
            if (possibleMatchScore.get(0) < autoMatchScore) {
                createReviewLinks(person, possibleMatchMasterPerson, possibleMatchScore);
            } else {
                linkPerson(person, possibleMatchMasterPerson.get(0), possibleMatchScore.get(0));
            }
        }
    }

    private void createFastMatchMasterPerson(Person person, MasterPerson masterPerson) {
        person.setMasterPerson(masterPerson);
        person.setPersonStatus(PersonStatus.FAST_MATCH);
        person.setScore(null);
        personRepository.save(person);
    }

    private void linkPerson(Person person, MasterPerson masterPerson, Double score) {
        person.setMasterPerson(masterPerson);
        person.setPersonStatus(PersonStatus.AUTO_MATCH);
        person.setScore(score);
        personRepository.save(person);
    }

    private void createReviewLinks(Person person, List<MasterPerson> possibleMatchMasterPerson, List<Double> possibleMatchScore) {
        person.setPersonStatus(PersonStatus.NEED_REVIEW);
        personRepository.save(person);

        for (int i = 0; i < possibleMatchMasterPerson.size(); i++) {
            MasterPerson masterPerson = possibleMatchMasterPerson.get(i);

            ReviewLink reviewLink = new ReviewLink();
            reviewLink.setPerson(person);
            reviewLink.setMasterPerson(masterPerson);
            reviewLink.setScore(possibleMatchScore.get(i));
            reviewLink.setLinkStatus(LinkStatus.NEED_REVIEW);
            reviewLinkRepository.save(reviewLink);
        }
    }

    private void createNewMasterPerson(Person person) {
        MasterPerson masterPerson = new MasterPerson();

        masterPerson.setGlobalId(generateGlobalIdService.generate(person));
        masterPerson.setFullName(person.getFullName());
        masterPerson.setHealthInsuranceNumber(person.getHealthInsuranceNumber());
        masterPerson.setIdentificationNumber(person.getIdentificationNumber());
        masterPerson.setDateOfBirth(person.getDateOfBirth());
        masterPerson.setAddress(person.getAddress());
        masterPerson.setGender(person.getGender());
        masterPerson.setProvince(person.getProvince());
        masterPerson = masterPersonRepository.save(masterPerson);

        person.setMasterPerson(masterPerson);
        person.setPersonStatus(PersonStatus.NEW_MASTER_PERSON);
        person.setScore(100.0);
        personRepository.save(person);
    }

    private double calculateScore(Person person, MasterPerson masterPerson, AlgorithmInterface algorithm, List<FieldWeight> fieldWeights) {
        double totalScore = 0;

        for (FieldWeight fieldWeight : fieldWeights) {
            Field field = fieldWeight.getField();
            int weight = fieldWeight.getWeight();
            double score = 0;

            if (field == Field.FULL_NAME) {
                score = algorithm.ratio(person.getFullName(), masterPerson.getFullName());
            } else if (field == Field.HEALTH_INSURANCE_NUMBER) {
                score = algorithm.ratio(person.getHealthInsuranceNumber(), masterPerson.getHealthInsuranceNumber());
            } else if (field == Field.IDENTIFICATION_NUMBER) {
                score = algorithm.ratio(person.getIdentificationNumber(), masterPerson.getIdentificationNumber());
            } else if (field == Field.ADDRESS) {
                score = algorithm.ratio(person.getAddress(), masterPerson.getAddress());
            } else if (field == Field.DATE_OF_BIRTH) {
                score = algorithm.ratio(person.getDateOfBirth().toString(), masterPerson.getDateOfBirth().toString());
            } else if (field == Field.GENDER) {
                score = algorithm.ratio(person.getGender().toString(), masterPerson.getGender().toString());
            }
            totalScore += score * weight;
        }

        return totalScore / 100;
    }
}
