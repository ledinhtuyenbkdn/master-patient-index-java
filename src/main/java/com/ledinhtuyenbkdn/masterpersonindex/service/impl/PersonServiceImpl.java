package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.exception.BusinessException;
import com.ledinhtuyenbkdn.masterpersonindex.model.HealthCenter;
import com.ledinhtuyenbkdn.masterpersonindex.model.Person;
import com.ledinhtuyenbkdn.masterpersonindex.model.User;
import com.ledinhtuyenbkdn.masterpersonindex.repository.HealthCenterRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.PersonRepository;
import com.ledinhtuyenbkdn.masterpersonindex.repository.UserRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.PersonService;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.PersonDTO;
import com.ledinhtuyenbkdn.masterpersonindex.service.mapper.PersonMapper;
import com.ledinhtuyenbkdn.masterpersonindex.service.util.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    private HealthCenterRepository healthCenterRepository;

    private PersonMapper personMapper;

    private UserRepository userRepository;

    public PersonServiceImpl(PersonRepository personRepository, HealthCenterRepository healthCenterRepository, PersonMapper personMapper, UserRepository userRepository) {
        this.personRepository = personRepository;
        this.healthCenterRepository = healthCenterRepository;
        this.personMapper = personMapper;
        this.userRepository = userRepository;
    }

    @Override
    public PersonDTO save(PersonDTO personDTO) {
        String userName = SecurityUtils.getCurrentUserName();
        Optional<User> userOptional = userRepository.findByUserName(userName);

        if (!userOptional.isPresent()) {
            throw new BusinessException("Not found user");
        }

        User user = userOptional.get();
        HealthCenter healthCenter = user.getHealthCenter();

        Person person = personMapper.toEntity(personDTO);
        person.setHealthCenter(healthCenter);
        return personMapper.toDto(personRepository.save(person));
    }

    @Override
    public Optional<PersonDTO> findOne(Long id) {
        return personRepository.findById(id).map(personMapper::toDto);
    }

    @Override
    public List<PersonDTO> findAll() {
        return personRepository.findAll().stream().map(personMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
