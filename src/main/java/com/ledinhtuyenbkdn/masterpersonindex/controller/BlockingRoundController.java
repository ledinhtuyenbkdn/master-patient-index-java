package com.ledinhtuyenbkdn.masterpersonindex.controller;

import com.ledinhtuyenbkdn.masterpersonindex.exception.BadRequestException;
import com.ledinhtuyenbkdn.masterpersonindex.model.BlockingRound;
import com.ledinhtuyenbkdn.masterpersonindex.service.BlockingRoundService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BlockingRoundController {

    private BlockingRoundService blockingRoundService;

    public BlockingRoundController(BlockingRoundService blockingRoundService) {
        this.blockingRoundService = blockingRoundService;
    }

    @PostMapping("/blocking-rounds")
    public ResponseEntity<BlockingRound> create(@RequestBody @Valid BlockingRound blockingRound) {
        if (blockingRound.getId() != null) {
            throw new BadRequestException("Id must be null.");
        }
        blockingRound = blockingRoundService.save(blockingRound);
        return ResponseEntity.status(HttpStatus.CREATED).body(blockingRound);
    }

    @PutMapping("/blocking-rounds")
    public ResponseEntity<BlockingRound> update(@RequestBody @Valid BlockingRound blockingRound) {
        if (blockingRound.getId() == null) {
            throw new BadRequestException("Id must be not null.");
        }
        blockingRound = blockingRoundService.update(blockingRound);
        return ResponseEntity.ok(blockingRound);
    }

    @GetMapping("/blocking-rounds/{id}")
    public ResponseEntity<BlockingRound> findById(@PathVariable("id") Long id) {
        Optional<BlockingRound> optionalBlockingRound = blockingRoundService.findOne(id);
        if (!optionalBlockingRound.isPresent()) {
            throw new BadRequestException("Not found.");
        }
        return ResponseEntity.ok(optionalBlockingRound.get());
    }

    @GetMapping("/blocking-rounds")
    public ResponseEntity<List<BlockingRound>> findAll() {
        return ResponseEntity.ok(blockingRoundService.findAll());
    }

    @DeleteMapping("/blocking-rounds/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        blockingRoundService.delete(id);
        return ResponseEntity.ok().build();
    }
}
