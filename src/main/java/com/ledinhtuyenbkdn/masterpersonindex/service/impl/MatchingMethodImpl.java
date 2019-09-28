package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.model.MatchingMethod;
import com.ledinhtuyenbkdn.masterpersonindex.repository.MatchingMethodRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.MatchingMethodService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MatchingMethodImpl implements MatchingMethodService {

    private MatchingMethodRepository matchingMethodRepository;

    public MatchingMethodImpl(MatchingMethodRepository matchingMethodRepository) {
        this.matchingMethodRepository = matchingMethodRepository;
    }

    @Override
    public MatchingMethod save(MatchingMethod matchingMethod) {
        return matchingMethodRepository.save(matchingMethod);
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
}
