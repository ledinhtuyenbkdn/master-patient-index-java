package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.exception.BadRequestException;
import com.ledinhtuyenbkdn.masterpersonindex.model.MatchingMethod;
import com.ledinhtuyenbkdn.masterpersonindex.service.MatchingMethodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MatchingMethodController {

    private MatchingMethodService matchingMethodService;

    public MatchingMethodController(MatchingMethodService matchingMethodService) {
        this.matchingMethodService = matchingMethodService;
    }

    @PostMapping("/matching-methods")
    public ResponseEntity<MatchingMethod> create(@RequestBody @Valid MatchingMethod matchingMethod) {
        if (matchingMethod.getId() != null) {
            throw new BadRequestException("Id must be null.");
        }
        matchingMethod = matchingMethodService.save(matchingMethod);
        return ResponseEntity.status(HttpStatus.CREATED).body(matchingMethod);
    }

    @PutMapping("/matching-methods")
    public ResponseEntity<MatchingMethod> update(@RequestBody @Valid MatchingMethod matchingMethod) {
        if (matchingMethod.getId() == null) {
            throw new BadRequestException("Id must be not null.");
        }
        matchingMethod = matchingMethodService.update(matchingMethod);
        return ResponseEntity.ok(matchingMethod);
    }

    @GetMapping("/matching-methods/{id}")
    public ResponseEntity<MatchingMethod> findById(@PathVariable("id") Long id) {
        Optional<MatchingMethod> optionalMatchingMethod = matchingMethodService.findOne(id);
        if (!optionalMatchingMethod.isPresent()) {
            throw new BadRequestException("Not found.");
        }
        return ResponseEntity.ok(optionalMatchingMethod.get());
    }

    @GetMapping("/matching-methods")
    public ResponseEntity<List<MatchingMethod>> findAll() {
        return ResponseEntity.ok(matchingMethodService.findAll());
    }

    @DeleteMapping("/matching-methods/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        matchingMethodService.delete(id);
        return ResponseEntity.ok().build();
    }
}
