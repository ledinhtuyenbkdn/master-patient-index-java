package com.ledinhtuyenbkdn.masterpersonindex.service.algorithm;

import com.ledinhtuyenbkdn.masterpersonindex.model.*;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.Field;
import com.ledinhtuyenbkdn.masterpersonindex.repository.BlockingRoundRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.MasterPersonRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockingImpl implements BlockingService {

    private MasterPersonRepository masterPersonRepository;

    private BlockingRoundRepository blockingRoundRepository;

    public BlockingImpl(MasterPersonRepository masterPersonRepository, BlockingRoundRepository blockingRoundRepository) {
        this.masterPersonRepository = masterPersonRepository;
        this.blockingRoundRepository = blockingRoundRepository;
    }

    @Override
    public List<MasterPerson> getCandidates(Person person) {
        List<BlockingRound> blockingRounds = blockingRoundRepository.findAll();
        Specification<MasterPerson> specification = buildSpecification(blockingRounds, person);
        return masterPersonRepository.findAll(specification);
    }

    private Specification<MasterPerson> buildSpecification(List<BlockingRound> blockingRounds, Person person) {
        Specification<MasterPerson> parentSpecification = Specification.where(null);

        for (BlockingRound blockingRound : blockingRounds) {
            Specification<MasterPerson> specification = Specification.where(null);

            for (BlockingField blockingField : blockingRound.getBlockingFields()) {
                Field field = blockingField.getField();

                if (field == Field.FULL_NAME) {
                    specification = specification.and(((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(MasterPerson_.fullName), person.getFullName())));
                } else if (field == Field.HEALTH_INSURANCE_NUMBER) {
                    specification = specification.and(((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(MasterPerson_.healthInsuranceNumber), person.getHealthInsuranceNumber())));
                } else if (field == Field.IDENTIFICATION_NUMBER) {
                    specification = specification.and(((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(MasterPerson_.identificationNumber), person.getIdentificationNumber())));
                } else if (field == Field.ADDRESS) {
                    specification = specification.and(((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(MasterPerson_.address), person.getAddress())));
                } else if (field == Field.DATE_OF_BIRTH) {
                    specification = specification.and(((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(MasterPerson_.dateOfBirth), person.getDateOfBirth())));
                } else if (field == Field.GENDER) {
                    specification = specification.and(((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(MasterPerson_.gender), person.getGender())));
                }
            }
            parentSpecification = parentSpecification.or(specification);
        }
        return parentSpecification;
    }
}
