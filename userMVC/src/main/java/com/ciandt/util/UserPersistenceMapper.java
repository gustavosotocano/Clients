package com.ciandt.util;

import com.ciandt.dto.UserDto;
import com.ciandt.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserPersistenceMapper {
    User toEntity(UserDto userDto);
    UserDto toModel(User user);
}
