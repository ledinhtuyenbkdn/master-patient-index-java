package com.ledinhtuyenbkdn.masterpersonindex.service;

import com.ledinhtuyenbkdn.masterpersonindex.model.BlockingRound;

import java.util.List;
import java.util.Optional;

public interface BlockingRoundService {

    BlockingRound save(BlockingRound blockingRound);

    BlockingRound update(BlockingRound blockingRound);

    Optional<BlockingRound> findOne(Long id);

    List<BlockingRound> findAll();

    void delete(Long id);
}
