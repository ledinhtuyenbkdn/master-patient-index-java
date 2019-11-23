package com.ledinhtuyenbkdn.masterpersonindex.service.mapper;

import com.ledinhtuyenbkdn.masterpersonindex.model.HealthCenter;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.HealthCenterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HealthCenterMapper {

    HealthCenterDTO toDto(HealthCenter healthCenter);

    @Mapping(target = "people", ignore = true)
    @Mapping(target = "users", ignore = true)
    HealthCenter toEntity(HealthCenterDTO healthCenter);
}
