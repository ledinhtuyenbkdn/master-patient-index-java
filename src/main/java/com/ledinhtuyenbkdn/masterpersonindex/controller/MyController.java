package com.ledinhtuyenbkdn.masterpersonindex.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/abc")
    public String getHello() {
        return "abc";
    }
}
