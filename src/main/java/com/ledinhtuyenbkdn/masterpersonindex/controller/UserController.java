package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.exception.BadRequestException;
import com.ledinhtuyenbkdn.masterpersonindex.model.User;
import com.ledinhtuyenbkdn.masterpersonindex.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        if (user.getId() != null) {
            throw new BadRequestException("Id must be null.");
        }
        user = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

//    @PutMapping("/health-centers")
//    public ResponseEntity<HealthCenter> update(@RequestBody @Valid HealthCenter healthCenter) {
//        if (healthCenter.getId() == null) {
//            throw new BadRequestException("Id must be not null.");
//        }
//        healthCenter = healthCenterService.save(healthCenter);
//        return ResponseEntity.ok(healthCenter);
//    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        Optional<User> userOptional = userService.findOne(id);
        if (!userOptional.isPresent()) {
            throw new BadRequestException("Not found.");
        }
        return ResponseEntity.ok(userOptional.get());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
