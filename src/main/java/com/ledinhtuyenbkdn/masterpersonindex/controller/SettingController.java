package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.model.Setting;
import com.ledinhtuyenbkdn.masterpersonindex.service.SettingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SettingController {

    private SettingService settingService;

    public SettingController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/settings")
    ResponseEntity<List<Setting>> getAllSettings() {
        return ResponseEntity.ok(settingService.findAll());
    }

    @PutMapping("/settings")
    ResponseEntity<Void> updateAllSettings(@RequestBody List<Setting> settings) {
        settingService.update(settings);
        return ResponseEntity.noContent().build();
    }
}
