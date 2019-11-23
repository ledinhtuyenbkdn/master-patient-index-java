package com.ledinhtuyenbkdn.masterpersonindex.service.mapper;

import com.ledinhtuyenbkdn.masterpersonindex.model.Person;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {HealthCenterMapper.class})
public interface PersonMapper {

    @Mapping(source = "healthCenter", target = "healthCenter")
    @Mapping(source = "masterPerson.id", target = "masterPersonId")
    PersonDTO toDto(Person person);

    @Mapping(source = "healthCenter", target = "healthCenter")
    @Mapping(target = "masterPerson", ignore = true)
    Person toEntity(PersonDTO person);
}
