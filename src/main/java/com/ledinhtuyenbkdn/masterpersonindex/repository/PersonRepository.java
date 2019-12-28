package com.ledinhtuyenbkdn.masterpersonindex.repository;

import com.ledinhtuyenbkdn.masterpersonindex.model.Person;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.PersonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    Long countByPersonStatus(PersonStatus personStatus);
}
