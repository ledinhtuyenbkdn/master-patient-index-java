package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.service.DashboardService;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.PersonStatisticDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DashboardController {

    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PersonStatisticDTO> getPersonStatistic() {
        return ResponseEntity.ok(dashboardService.getPersonStatistic());
    }
}
