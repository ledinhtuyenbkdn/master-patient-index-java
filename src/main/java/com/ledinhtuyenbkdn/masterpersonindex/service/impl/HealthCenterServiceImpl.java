package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.model.HealthCenter;
import com.ledinhtuyenbkdn.masterpersonindex.repository.HealthCenterRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.HealthCenterService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HealthCenterServiceImpl implements HealthCenterService {

    private HealthCenterRepository healthCenterRepository;

    public HealthCenterServiceImpl(HealthCenterRepository healthCenterRepository) {
        this.healthCenterRepository = healthCenterRepository;
    }

    @Override
    public HealthCenter save(HealthCenter healthCenter) {
        return healthCenterRepository.save(healthCenter);
    }

    @Override
    public Optional<HealthCenter> findOne(Long id) {
        return healthCenterRepository.findById(id);
    }

    @Override
    public List<HealthCenter> findAll() {
        return healthCenterRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        healthCenterRepository.deleteById(id);
    }
}
