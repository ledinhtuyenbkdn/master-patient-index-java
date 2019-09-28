package com.ledinhtuyenbkdn.masterpersonindex.service;

import com.ledinhtuyenbkdn.masterpersonindex.model.HealthCenter;

import java.util.List;
import java.util.Optional;

public interface HealthCenterService {

    HealthCenter save(HealthCenter healthCenter);

    Optional<HealthCenter> findOne(Long id);

    List<HealthCenter> findAll();

    void delete(Long id);
}
