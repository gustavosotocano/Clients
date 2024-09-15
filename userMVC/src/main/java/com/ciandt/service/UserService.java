package com.ciandt.service;

import com.ciandt.dto.UserDto;
import com.ciandt.entity.User;
import com.ciandt.exception.RequestException;
import com.ciandt.exception.ResourceNotFoundException;
import com.ciandt.repository.UserJpaRepository;
import com.ciandt.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService implements UserServiceI {


    private final UserJpaRepository userJpaRepository;

    public UserService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        var users = userJpaRepository.findByEmailAddress(userDto.getEmailAddress());
        if (users.isPresent()) {
            throw new RequestException("004", "User is already created");
        }
        var user = userJpaRepository.save(Utils.toUser(userDto));

        return Utils.toUserDto(user);
    }


    @Transactional
    @Override
    public UserDto updateUser(long id, UserDto userDetails) {


        Optional<User> userOpt = Optional.ofNullable(userJpaRepository.findUserForUpdate(id)
                                                             .orElseThrow(() -> new ResourceNotFoundException("001",
                                                                                                              "User Not Found")));
        User user = userOpt.get();
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmailAddress(userDetails.getEmailAddress());

        var users = userJpaRepository.save(user);

        return Utils.toUserDto(users);
    }

    public UserDto getUser(Long id) {
        var user = userJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("001", "User Not Found"));

        return Utils.toUserDto(user);
    }


    @Override
    public List<UserDto> findAll() {
        return userJpaRepository.findAll().stream()
                .map(Utils::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findByEmailAddress(String email) {
        var users = userJpaRepository.findByEmailAddress(email).orElseThrow(() -> new ResourceNotFoundException("001"
                , "User" +
                " Not Found"));


        return Utils.toUserDto(users);
                /*.stream()
                .map(this::toClientDto)
                .collect(Collectors.toList());*/
    }


}
