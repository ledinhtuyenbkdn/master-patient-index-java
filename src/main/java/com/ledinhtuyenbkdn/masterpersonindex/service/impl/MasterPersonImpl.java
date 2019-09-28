package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.model.MasterPerson;
import com.ledinhtuyenbkdn.masterpersonindex.repository.MasterPersonRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.MasterPersonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MasterPersonImpl implements MasterPersonService {

    private MasterPersonRepository masterPersonRepository;

    public MasterPersonImpl(MasterPersonRepository masterPersonRepository) {
        this.masterPersonRepository = masterPersonRepository;
    }

    @Override
    public MasterPerson save(MasterPerson masterPerson) {
        return masterPersonRepository.save(masterPerson);
    }

    @Override
    public Optional<MasterPerson> findOne(Long id) {
        return masterPersonRepository.findById(id);
    }

    @Override
    public List<MasterPerson> findAll() {
        return masterPersonRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        masterPersonRepository.deleteById(id);
    }
}
