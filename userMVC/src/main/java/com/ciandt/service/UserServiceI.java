package com.ciandt.service;


import com.ciandt.dto.UserDto;
import com.ciandt.entity.User;

import java.util.List;

public interface UserServiceI {

    UserDto createUser(UserDto user);
    List<UserDto> findAll();
    UserDto findByEmailAddress(String email) ;
    UserDto updateUser(long id, UserDto userDetails) ;
}
