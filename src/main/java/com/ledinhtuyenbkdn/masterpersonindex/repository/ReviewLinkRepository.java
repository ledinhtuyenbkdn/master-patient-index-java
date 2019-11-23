package com.ledinhtuyenbkdn.masterpersonindex.repository;

import com.ledinhtuyenbkdn.masterpersonindex.model.ReviewLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReviewLinkRepository extends JpaRepository<ReviewLink, Long>, JpaSpecificationExecutor<ReviewLink> {

    List<ReviewLink> findAllByPersonId(Long id);
}
