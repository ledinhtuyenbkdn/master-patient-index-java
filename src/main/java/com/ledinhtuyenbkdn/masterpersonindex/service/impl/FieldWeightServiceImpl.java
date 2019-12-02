package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.model.FieldWeight;
import com.ledinhtuyenbkdn.masterpersonindex.repository.FieldWeightRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.FieldWeightService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FieldWeightServiceImpl implements FieldWeightService {

    private FieldWeightRepository fieldWeightRepository;

    public FieldWeightServiceImpl(FieldWeightRepository fieldWeightRepository) {
        this.fieldWeightRepository = fieldWeightRepository;
    }

    @Override
    public List<FieldWeight> update(List<FieldWeight> fieldWeights) {
        validateEnoughTotalWeight(fieldWeights);
        return fieldWeightRepository.saveAll(fieldWeights);
    }

    private void validateEnoughTotalWeight(List<FieldWeight> fieldWeights) {
        int totalWeight = 0;

        for (int i = 0; i < fieldWeights.size(); i++) {
            totalWeight += fieldWeights.get(i).getWeight();
        }

        if (totalWeight != 100) {
            throw new RuntimeException("Total weight must be 100");
        }
    }

    @Override
    public List<FieldWeight> findAll() {
        return fieldWeightRepository.findAll();
    }
}
