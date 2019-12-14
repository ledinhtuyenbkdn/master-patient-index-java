package com.ledinhtuyenbkdn.masterpersonindex.repository;

import com.ledinhtuyenbkdn.masterpersonindex.model.MasterPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MasterPersonRepository extends JpaRepository<MasterPerson, Long>, JpaSpecificationExecutor<MasterPerson> {

    Optional<MasterPerson> findByHealthInsuranceNumberOrIdentificationNumber(String healthInsuranceNumber, String identificationNumber);
}
