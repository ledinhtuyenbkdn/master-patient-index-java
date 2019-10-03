package com.ledinhtuyenbkdn.masterpersonindex.runner;

import com.ledinhtuyenbkdn.masterpersonindex.model.BlockingField;
import com.ledinhtuyenbkdn.masterpersonindex.model.BlockingRound;
import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.Field;
import com.ledinhtuyenbkdn.masterpersonindex.repository.BlockingFieldRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.BlockingRoundRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.algorithm.BlockingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private BlockingService blockingService;

    @Autowired
    private BlockingRoundRepository blockingRoundRepository;

    @Autowired
    private BlockingFieldRepository blockingFieldRepository;

    @Override
    public void run(String... args) throws Exception {
        BlockingRound blockingRound = new BlockingRound();
        blockingRound.setName("abc");
        blockingRound = blockingRoundRepository.save(blockingRound);

        BlockingField address = new BlockingField();
        address.setField(Field.ADDRESS);
        address.setBlockingRound(blockingRound);

        BlockingField fullName = new BlockingField();
        fullName.setField(Field.FULL_NAME);
        fullName.setBlockingRound(blockingRound);

        blockingFieldRepository.save(address);
        blockingFieldRepository.save(fullName);


        blockingService.getCandidates(null);
    }
}
