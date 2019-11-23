package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.model.MasterPerson;
import com.ledinhtuyenbkdn.masterpersonindex.repository.MasterPersonRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.MasterPersonService;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.MasterPersonDTO;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.PersonDTO;
import com.ledinhtuyenbkdn.masterpersonindex.service.mapper.MasterPersonMapper;
import com.ledinhtuyenbkdn.masterpersonindex.service.mapper.PersonMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MasterPersonServiceImpl implements MasterPersonService {

    private MasterPersonRepository masterPersonRepository;

    private MasterPersonMapper masterPersonMapper;

    private PersonMapper personMapper;

    public MasterPersonServiceImpl(MasterPersonRepository masterPersonRepository, MasterPersonMapper masterPersonMapper, PersonMapper personMapper) {
        this.masterPersonRepository = masterPersonRepository;
        this.masterPersonMapper = masterPersonMapper;
        this.personMapper = personMapper;
    }

    @Override
    public MasterPersonDTO save(MasterPersonDTO masterPersonDTO) {
        MasterPerson masterPerson = masterPersonMapper.toEntity(masterPersonDTO);
        MasterPerson result = masterPersonRepository.save(masterPerson);
        return masterPersonMapper.toDto(result);
    }

    @Override
    public Optional<MasterPersonDTO> findOne(Long id) {
        return masterPersonRepository.findById(id).map(this::convertToMasterPersonDTO);
    }

    @Override
    public List<MasterPersonDTO> findAll() {
        return masterPersonRepository.findAll()
                .stream()
                .map(this::convertToMasterPersonDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        masterPersonRepository.deleteById(id);
    }

    private MasterPersonDTO convertToMasterPersonDTO(MasterPerson masterPerson) {
        MasterPersonDTO masterPersonDTO = masterPersonMapper.toDto(masterPerson);
        List<PersonDTO> persons = new ArrayList<>();
        masterPerson.getPeople().forEach(person -> {
            PersonDTO personDTO = personMapper.toDto(person);
            persons.add(personDTO);
        });
        masterPersonDTO.setPeople(persons);

        return masterPersonDTO;
    }
}
