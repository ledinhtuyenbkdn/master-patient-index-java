package com.ledinhtuyenbkdn.masterpersonindex.service;

import com.ledinhtuyenbkdn.masterpersonindex.model.MatchingMethod;

import java.util.List;
import java.util.Optional;

public interface MatchingMethodService {

    MatchingMethod save(MatchingMethod matchingMethod);

    Optional<MatchingMethod> findOne(Long id);

    List<MatchingMethod> findAll();

    void delete(Long id);
}
