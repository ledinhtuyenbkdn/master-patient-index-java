package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.exception.BadRequestException;
import com.ledinhtuyenbkdn.masterpersonindex.model.HealthCenter;
import com.ledinhtuyenbkdn.masterpersonindex.service.HealthCenterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HealthCenterController {

    private HealthCenterService healthCenterService;

    public HealthCenterController(HealthCenterService healthCenterService) {
        this.healthCenterService = healthCenterService;
    }

    @PostMapping("/health-centers")
    public ResponseEntity<HealthCenter> create(@RequestBody @Valid HealthCenter healthCenter) {
        if (healthCenter.getId() != null) {
            throw new BadRequestException("Id must be null.");
        }
        healthCenter = healthCenterService.save(healthCenter);
        return ResponseEntity.status(HttpStatus.CREATED).body(healthCenter);
    }

    @PutMapping("/health-centers")
    public ResponseEntity<HealthCenter> update(@RequestBody @Valid HealthCenter healthCenter) {
        if (healthCenter.getId() == null) {
            throw new BadRequestException("Id must be not null.");
        }
        healthCenter = healthCenterService.save(healthCenter);
        return ResponseEntity.ok(healthCenter);
    }

    @GetMapping("/health-centers/{id}")
    public ResponseEntity<HealthCenter> findById(@PathVariable("id") Long id) {
        Optional<HealthCenter> optionalHealthCenter = healthCenterService.findOne(id);
        if (!optionalHealthCenter.isPresent()) {
            throw new BadRequestException("Not found.");
        }
        return ResponseEntity.ok(optionalHealthCenter.get());
    }

    @GetMapping("/health-centers")
    public ResponseEntity<List<HealthCenter>> findAll() {
        return ResponseEntity.ok(healthCenterService.findAll());
    }

    @DeleteMapping("/health-centers/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        healthCenterService.delete(id);
        return ResponseEntity.ok().build();
    }
}
