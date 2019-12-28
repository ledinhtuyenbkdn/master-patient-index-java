package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.model.enumeration.PersonStatus;
import com.ledinhtuyenbkdn.masterpersonindex.repository.PersonRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.DashboardService;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.PersonStatisticDTO;
import org.springframework.stereotype.Service;

@Service
public class DashboardImpl implements DashboardService {

    private PersonRepository personRepository;

    public DashboardImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonStatisticDTO getPersonStatistic() {
        PersonStatisticDTO personStatisticDTO = new PersonStatisticDTO();

        personStatisticDTO.setNewMasterPerson(personRepository.countByPersonStatus(PersonStatus.NEW_MASTER_PERSON));
        personStatisticDTO.setNeedReview(personRepository.countByPersonStatus(PersonStatus.NEED_REVIEW));
        personStatisticDTO.setManualMatch(personRepository.countByPersonStatus(PersonStatus.MANUAL_MATCH));
        personStatisticDTO.setAutoMatch(personRepository.countByPersonStatus(PersonStatus.AUTO_MATCH));
        personStatisticDTO.setFastMatch(personRepository.countByPersonStatus(PersonStatus.FAST_MATCH));

        return personStatisticDTO;
    }
}
