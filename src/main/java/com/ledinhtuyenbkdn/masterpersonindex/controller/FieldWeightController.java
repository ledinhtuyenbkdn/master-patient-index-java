package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.model.FieldWeight;
import com.ledinhtuyenbkdn.masterpersonindex.service.FieldWeightService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FieldWeightController {

    private FieldWeightService fieldWeightService;

    public FieldWeightController(FieldWeightService fieldWeightService) {
        this.fieldWeightService = fieldWeightService;
    }

    @GetMapping("/field-weights")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<FieldWeight>> getAllFieldWeights() {
        return ResponseEntity.ok(fieldWeightService.findAll());
    }

    @PutMapping("/field-weights")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Void> updateAllFieldWeights(@RequestBody List<FieldWeight> fieldWeights) {
        fieldWeightService.update(fieldWeights);
        return ResponseEntity.noContent().build();
    }
}
