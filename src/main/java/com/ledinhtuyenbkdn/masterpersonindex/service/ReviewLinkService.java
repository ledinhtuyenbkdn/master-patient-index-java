package com.ledinhtuyenbkdn.masterpersonindex.service;

import com.ledinhtuyenbkdn.masterpersonindex.model.ReviewLink;

import java.util.List;
import java.util.Optional;

public interface ReviewLinkService {

    Optional<ReviewLink> findOne(Long id);

    List<ReviewLink> findAll();

    void matchPersonToMasterPerson(Long reviewLinkId);

    void rejectLinkPersonToMasterPerson(Long reviewLinkId);
}
