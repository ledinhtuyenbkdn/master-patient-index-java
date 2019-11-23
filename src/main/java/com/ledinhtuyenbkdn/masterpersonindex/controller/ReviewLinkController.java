package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.exception.BadRequestException;
import com.ledinhtuyenbkdn.masterpersonindex.model.ReviewLink;
import com.ledinhtuyenbkdn.masterpersonindex.service.ReviewLinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReviewLinkController {

    private ReviewLinkService reviewLinkService;

    public ReviewLinkController(ReviewLinkService reviewLinkService) {
        this.reviewLinkService = reviewLinkService;
    }

    @GetMapping("/review-links/{id}")
    public ResponseEntity<ReviewLink> findById(@PathVariable("id") Long id) {
        Optional<ReviewLink> reviewLinkOptional = reviewLinkService.findOne(id);
        if (!reviewLinkOptional.isPresent()) {
            throw new BadRequestException("Not found.");
        }
        return ResponseEntity.ok(reviewLinkOptional.get());
    }

    @GetMapping("/review-links")
    public ResponseEntity<List<ReviewLink>> findAll() {
        return ResponseEntity.ok(reviewLinkService.findAll());
    }

    @PostMapping("/review-links/{id}/match")
    public ResponseEntity<Void> matchPersonToMasterPerson(@PathVariable("id") Long id) {
        reviewLinkService.matchPersonToMasterPerson(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/review-links/{id}/reject")
    public ResponseEntity<Void> rejectLinkPersonToMasterPerson(@PathVariable("id") Long id) {
        reviewLinkService.rejectLinkPersonToMasterPerson(id);
        return ResponseEntity.noContent().build();
    }
}
