package com.ledinhtuyenbkdn.masterpersonindex.repository;

import com.ledinhtuyenbkdn.masterpersonindex.domain.MasterPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterPersonRepository extends JpaRepository<MasterPerson, Long> {
}
