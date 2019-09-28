package com.ledinhtuyenbkdn.masterpersonindex.repository;

import com.ledinhtuyenbkdn.masterpersonindex.model.MasterPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterPersonRepository extends JpaRepository<MasterPerson, Long> {
}
