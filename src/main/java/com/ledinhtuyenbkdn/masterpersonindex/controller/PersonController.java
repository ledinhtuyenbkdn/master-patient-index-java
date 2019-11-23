package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.exception.BadRequestException;
import com.ledinhtuyenbkdn.masterpersonindex.service.PersonService;
import com.ledinhtuyenbkdn.masterpersonindex.service.algorithm.MatchingService;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    private PersonService personService;

    private MatchingService matchingService;

    public PersonController(PersonService personService, MatchingService matchingService) {
        this.personService = personService;
        this.matchingService = matchingService;
    }

    @PostMapping("/persons")
    public ResponseEntity<PersonDTO> create(@RequestBody @Valid PersonDTO person) {
        if (person.getId() != null) {
            throw new BadRequestException("Id must be null.");
        }
        person = personService.save(person);

        matchingService.match(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.findOne(person.getId()).get());
    }

    @PutMapping("/persons")
    public ResponseEntity<PersonDTO> update(@RequestBody @Valid PersonDTO person) {
        if (person.getId() == null) {
            throw new BadRequestException("Id must be not null.");
        }
        person = personService.save(person);
        return ResponseEntity.ok(person);
    }

    @GetMapping("/persons/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id) {
        Optional<PersonDTO> optionalPerson = personService.findOne(id);
        if (!optionalPerson.isPresent()) {
            throw new BadRequestException("Not found.");
        }
        return ResponseEntity.ok(optionalPerson.get());
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonDTO>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        personService.delete(id);
        return ResponseEntity.ok().build();
    }
}
