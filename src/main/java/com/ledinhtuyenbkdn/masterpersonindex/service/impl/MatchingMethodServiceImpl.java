package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.model.FieldWeight;
import com.ledinhtuyenbkdn.masterpersonindex.model.MatchingMethod;
import com.ledinhtuyenbkdn.masterpersonindex.repository.FieldWeightRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.MatchingMethodRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.MatchingMethodService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MatchingMethodServiceImpl implements MatchingMethodService {

    private MatchingMethodRepository matchingMethodRepository;

    private FieldWeightRepository fieldWeightRepository;

    public MatchingMethodServiceImpl(MatchingMethodRepository matchingMethodRepository, FieldWeightRepository fieldWeightRepository) {
        this.matchingMethodRepository = matchingMethodRepository;
        this.fieldWeightRepository = fieldWeightRepository;
    }

    @Override
    public MatchingMethod save(MatchingMethod matchingMethod) {
        MatchingMethod result = matchingMethodRepository.save(matchingMethod);
        createFieldWeights(result, matchingMethod.getFieldWeights());
        return result;
    }

    @Override
    public MatchingMethod update(MatchingMethod matchingMethod) {
        MatchingMethod result = matchingMethodRepository.save(matchingMethod);
        deleteFieldWeights(matchingMethod);
        createFieldWeights(result, matchingMethod.getFieldWeights());
        return result;
    }

    @Override
    public Optional<MatchingMethod> findOne(Long id) {
        return matchingMethodRepository.findById(id);
    }

    @Override
    public List<MatchingMethod> findAll() {
        return matchingMethodRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        matchingMethodRepository.deleteById(id);
    }

    private void createFieldWeights(MatchingMethod matchingMethod, List<FieldWeight> fieldWeights) {
        fieldWeights.forEach(fieldWeight -> {
            fieldWeight.setMatchingMethod(matchingMethod);
            fieldWeightRepository.save(fieldWeight);
        });
    }

    private void deleteFieldWeights(MatchingMethod matchingMethod) {
        fieldWeightRepository.findByMatchingMethodId(matchingMethod.getId()).forEach(fieldWeight -> {
            fieldWeightRepository.delete(fieldWeight);
        });
    }
}
