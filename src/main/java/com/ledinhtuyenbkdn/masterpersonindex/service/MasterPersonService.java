package com.ledinhtuyenbkdn.masterpersonindex.service;

import com.ledinhtuyenbkdn.masterpersonindex.model.MasterPerson;

import java.util.List;
import java.util.Optional;

public interface MasterPersonService {

    MasterPerson save(MasterPerson masterPerson);

    Optional<MasterPerson> findOne(Long id);

    List<MasterPerson> findAll();

    void delete(Long id);
}
