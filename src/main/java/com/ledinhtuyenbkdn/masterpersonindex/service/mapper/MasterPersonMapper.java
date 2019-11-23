package com.ledinhtuyenbkdn.masterpersonindex.service.mapper;

import com.ledinhtuyenbkdn.masterpersonindex.model.MasterPerson;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.MasterPersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MasterPersonMapper {

    @Mapping(target = "people", ignore = true)
    MasterPersonDTO toDto(MasterPerson masterPerson);

    @Mapping(target = "people", ignore = true)
    MasterPerson toEntity(MasterPersonDTO masterPerson);
}
