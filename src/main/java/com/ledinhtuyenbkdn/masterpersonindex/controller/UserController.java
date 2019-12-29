package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.exception.BadRequestException;
import com.ledinhtuyenbkdn.masterpersonindex.model.User;
import com.ledinhtuyenbkdn.masterpersonindex.service.UserService;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO userDTO) {
        if (userDTO.getId() != null) {
            throw new BadRequestException("Id must be null.");
        }
        userDTO = userService.save(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> findById(@PathVariable("id") Long id) {
        Optional<User> userOptional = userService.findOne(id);
        if (!userOptional.isPresent()) {
            throw new BadRequestException("Not found.");
        }
        return ResponseEntity.ok(userOptional.get());
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
