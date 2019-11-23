package com.ledinhtuyenbkdn.masterpersonindex.service;

import com.ledinhtuyenbkdn.masterpersonindex.service.dto.PersonDTO;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    PersonDTO save(PersonDTO person);

    Optional<PersonDTO> findOne(Long id);

    List<PersonDTO> findAll();

    void delete(Long id);
}
