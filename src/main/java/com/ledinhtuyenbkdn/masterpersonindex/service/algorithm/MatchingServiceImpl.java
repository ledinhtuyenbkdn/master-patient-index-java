package com.ledinhtuyenbkdn.masterpersonindex.service.algorithm;

import com.ledinhtuyenbkdn.masterpersonindex.model.*;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.Field;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.LinkStatus;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.PersonStatus;
import com.ledinhtuyenbkdn.masterpersonindex.repository.MasterPersonRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.MatchingMethodRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.PersonRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.ReviewLinkRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.PersonDTO;
import com.ledinhtuyenbkdn.masterpersonindex.service.mapper.PersonMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchingServiceImpl implements MatchingService {

    private static final int MANUAL = 60;

    private static final int AUTO = 80;

    private static final Long MATCHING_METHOD = 1L;

    private BlockingService blockingService;

    private MatchingMethodRepository matchingMethodRepository;

    private PersonRepository personRepository;

    private MasterPersonRepository masterPersonRepository;

    private ReviewLinkRepository reviewLinkRepository;

    private PersonMapper personMapper;

    public MatchingServiceImpl(BlockingService blockingService, MatchingMethodRepository matchingMethodRepository, PersonRepository personRepository, MasterPersonRepository masterPersonRepository, ReviewLinkRepository reviewLinkRepository, PersonMapper personMapper) {
        this.blockingService = blockingService;
        this.matchingMethodRepository = matchingMethodRepository;
        this.personRepository = personRepository;
        this.masterPersonRepository = masterPersonRepository;
        this.reviewLinkRepository = reviewLinkRepository;
        this.personMapper = personMapper;
    }

    @Override
    public void match(PersonDTO personDTO) {
        Person person = personMapper.toEntity(personDTO);

        AlgorithmInterface algorithm = AlgorithmFactory.getAlgorithm(AlgorithmFactory.FUZZY_SEARCH);
        Optional<MatchingMethod> optionalMatchingMethod = matchingMethodRepository.findById(MATCHING_METHOD);

        List<MasterPerson> candidates = blockingService.getCandidates(person);
        List<MasterPerson> possibleMatchMasterPerson = new ArrayList<>();
        List<Double> possibleMatchScore = new ArrayList<>();

        if (!optionalMatchingMethod.isPresent()) {
            throw new RuntimeException("Matching method does not exist.");
        }

        for (MasterPerson masterPerson : candidates) {
            double score = calculateScore(person, masterPerson, algorithm, optionalMatchingMethod.get());
            if (score > MANUAL) {
                possibleMatchMasterPerson.add(masterPerson);
                possibleMatchScore.add(score);
            }
        }

        if (possibleMatchMasterPerson.isEmpty()) {
            createNewMasterPerson(person);
        } else if (possibleMatchMasterPerson.size() > 1) {
            createReviewLinks(person, possibleMatchMasterPerson, possibleMatchScore);
        } else {
            if (possibleMatchScore.get(0) < AUTO) {
                createReviewLinks(person, possibleMatchMasterPerson, possibleMatchScore);
            } else {
                linkPerson(person, possibleMatchMasterPerson.get(0), possibleMatchScore.get(0));
            }
        }
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

        masterPerson.setFullName(person.getFullName());
        masterPerson.setHealthInsuranceNumber(person.getHealthInsuranceNumber());
        masterPerson.setIdentificationNumber(person.getIdentificationNumber());
        masterPerson.setDateOfBirth(person.getDateOfBirth());
        masterPerson.setAddress(person.getAddress());
        masterPerson.setGender(person.getGender());

        masterPerson = masterPersonRepository.save(masterPerson);

        person.setMasterPerson(masterPerson);
        person.setPersonStatus(PersonStatus.NEW_MASTER_PERSON);
        person.setScore(100.0);
        personRepository.save(person);
    }

    private double calculateScore(Person person, MasterPerson masterPerson, AlgorithmInterface algorithm, MatchingMethod matchingMethod) {
        double totalScore = 0;

        for (FieldWeight fieldWeight : matchingMethod.getFieldWeights()) {
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
