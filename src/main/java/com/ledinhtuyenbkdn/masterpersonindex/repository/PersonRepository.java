package com.ledinhtuyenbkdn.masterpersonindex.repository;

import com.ledinhtuyenbkdn.masterpersonindex.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
}
