package com.ledinhtuyenbkdn.masterpersonindex.repository;

import com.ledinhtuyenbkdn.masterpersonindex.model.FieldWeight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldWeightRepository extends JpaRepository<FieldWeight, Long> {
    List<FieldWeight> findByMatchingMethodId(Long id);
}
