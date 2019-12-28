package com.ledinhtuyenbkdn.masterpersonindex.repository;

import com.ledinhtuyenbkdn.masterpersonindex.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProvinceRepository extends JpaRepository<Province, Long>, JpaSpecificationExecutor<Province> {
}
