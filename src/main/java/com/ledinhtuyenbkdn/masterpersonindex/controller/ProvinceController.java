package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.model.Province;
import com.ledinhtuyenbkdn.masterpersonindex.service.ProvinceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProvinceController {

    private ProvinceService provinceService;

    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping("/provinces")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<Province>> findAll() {
        return ResponseEntity.ok(provinceService.findAll());
    }
}
