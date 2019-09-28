package com.ledinhtuyenbkdn.masterpersonindex.service;

import com.ledinhtuyenbkdn.masterpersonindex.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    Person save(Person person);

    Optional<Person> findOne(Long id);

    List<Person> findAll();

    void delete(Long id);
}
