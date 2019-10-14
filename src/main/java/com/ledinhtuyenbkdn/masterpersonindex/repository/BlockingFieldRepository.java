package com.ledinhtuyenbkdn.masterpersonindex.repository;

import com.ledinhtuyenbkdn.masterpersonindex.model.BlockingField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockingFieldRepository extends JpaRepository<BlockingField, Long> {
    List<BlockingField> findByBlockingRoundId(Long id);
}
