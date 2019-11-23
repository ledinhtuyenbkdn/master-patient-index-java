package com.ledinhtuyenbkdn.masterpersonindex.service;

import com.ledinhtuyenbkdn.masterpersonindex.service.dto.MasterPersonDTO;

import java.util.List;
import java.util.Optional;

public interface MasterPersonService {

    MasterPersonDTO save(MasterPersonDTO masterPersonDTO);

    Optional<MasterPersonDTO> findOne(Long id);

    List<MasterPersonDTO> findAll();

    void delete(Long id);
}
