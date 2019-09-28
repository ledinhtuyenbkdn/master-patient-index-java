package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.model.BlockingRound;
import com.ledinhtuyenbkdn.masterpersonindex.repository.BlockingRoundRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.BlockingRoundService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BlockingRoundImpl implements BlockingRoundService {

    private BlockingRoundRepository blockingRoundRepository;

    public BlockingRoundImpl(BlockingRoundRepository blockingRoundRepository) {
        this.blockingRoundRepository = blockingRoundRepository;
    }

    @Override
    public BlockingRound save(BlockingRound blockingRound) {
        return blockingRoundRepository.save(blockingRound);
    }

    @Override
    public Optional<BlockingRound> findOne(Long id) {
        return blockingRoundRepository.findById(id);
    }

    @Override
    public List<BlockingRound> findAll() {
        return blockingRoundRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        blockingRoundRepository.deleteById(id);
    }
}
