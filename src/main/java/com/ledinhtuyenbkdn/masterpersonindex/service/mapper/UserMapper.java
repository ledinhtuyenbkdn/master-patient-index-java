package com.ledinhtuyenbkdn.masterpersonindex.service.mapper;

import com.ledinhtuyenbkdn.masterpersonindex.model.User;
import com.ledinhtuyenbkdn.masterpersonindex.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);
}
