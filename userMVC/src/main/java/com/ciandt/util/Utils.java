package com.ciandt.util;

import com.ciandt.dto.UserDto;
import com.ciandt.entity.User;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Utils {
    public static User toUser(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .emailAddress(userDto.getEmailAddress())
                .lastName(userDto.getLastName())
                .build();
    }


    public static UserDto toUserDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .emailAddress(user.getEmailAddress())
                .lastName(user.getLastName())
                .build();
    }

}
