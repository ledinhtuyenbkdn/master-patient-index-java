package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.model.BlockingField;
import com.ledinhtuyenbkdn.masterpersonindex.model.BlockingRound;
import com.ledinhtuyenbkdn.masterpersonindex.repository.BlockingFieldRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.BlockingRoundRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.BlockingRoundService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BlockingRoundServiceImpl implements BlockingRoundService {

    private BlockingRoundRepository blockingRoundRepository;

    private BlockingFieldRepository blockingFieldRepository;

    public BlockingRoundServiceImpl(BlockingRoundRepository blockingRoundRepository, BlockingFieldRepository blockingFieldRepository) {
        this.blockingRoundRepository = blockingRoundRepository;
        this.blockingFieldRepository = blockingFieldRepository;
    }

    @Override
    public BlockingRound save(BlockingRound blockingRound) {
        BlockingRound result = blockingRoundRepository.save(blockingRound);
        createBlockingFields(result, blockingRound.getBlockingFields());
        return result;
    }

    @Override
    public BlockingRound update(BlockingRound blockingRound) {
        BlockingRound result = blockingRoundRepository.save(blockingRound);
        deleteBlockingFields(blockingRound);
        createBlockingFields(result, blockingRound.getBlockingFields());
        return result;
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


    private void createBlockingFields(BlockingRound blockingRound, List<BlockingField> blockingFields) {
        blockingFields.forEach(blockingField -> {
            blockingField.setBlockingRound(blockingRound);
            blockingFieldRepository.save(blockingField);
        });
    }

    private void deleteBlockingFields(BlockingRound blockingRound) {
        blockingFieldRepository.findByBlockingRoundId(blockingRound.getId()).forEach(blockingField -> {
            blockingFieldRepository.delete(blockingField);
        });
    }
}
