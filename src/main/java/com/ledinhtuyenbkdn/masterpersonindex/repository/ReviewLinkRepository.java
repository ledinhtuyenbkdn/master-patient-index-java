package com.ledinhtuyenbkdn.masterpersonindex.repository;

import com.ledinhtuyenbkdn.masterpersonindex.model.ReviewLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReviewLinkRepository extends JpaRepository<ReviewLink, Long>, JpaSpecificationExecutor<ReviewLink> {
}
