package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.exception.BadRequestException;
import com.ledinhtuyenbkdn.masterpersonindex.service.MasterPersonService;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.MasterPersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MasterPersonController {

    private MasterPersonService masterPersonService;

    public MasterPersonController(MasterPersonService masterPersonService) {
        this.masterPersonService = masterPersonService;
    }

    //    @PostMapping("/master-persons")
    public ResponseEntity<MasterPersonDTO> create(@RequestBody @Valid MasterPersonDTO masterPerson) {
        if (masterPerson.getId() != null) {
            throw new BadRequestException("Id must be null.");
        }
        masterPerson = masterPersonService.save(masterPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(masterPerson);
    }

    //    @PutMapping("/master-persons")
    public ResponseEntity<MasterPersonDTO> update(@RequestBody @Valid MasterPersonDTO masterPerson) {
        if (masterPerson.getId() == null) {
            throw new BadRequestException("Id must be not null.");
        }
        masterPerson = masterPersonService.save(masterPerson);
        return ResponseEntity.ok(masterPerson);
    }

    @GetMapping("/master-persons/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<MasterPersonDTO> findById(@PathVariable("id") Long id) {
        Optional<MasterPersonDTO> optionalMasterPerson = masterPersonService.findOne(id);
        if (!optionalMasterPerson.isPresent()) {
            throw new BadRequestException("Not found.");
        }
        return ResponseEntity.ok(optionalMasterPerson.get());
    }

    @GetMapping("/master-persons")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<MasterPersonDTO>> findAll() {
        return ResponseEntity.ok(masterPersonService.findAll());
    }

    //    @DeleteMapping("/master-persons/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        masterPersonService.delete(id);
        return ResponseEntity.ok().build();
    }
}
