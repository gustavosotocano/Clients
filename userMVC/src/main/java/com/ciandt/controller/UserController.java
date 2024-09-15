package com.ciandt.controller;

import com.ciandt.dto.UserDto;
import com.ciandt.service.UserService;
import com.ciandt.util.JsonUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/my-project/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestBody String userJson) {
        var user = JsonUtil.deserializeJson(userJson, UserDto.class);
        var createdUser = userService.createUser(user);
        return JsonUtil.serializeJson(Map.of("succeeded", createdUser != null, "id", createdUser.getId()));
    }

    @PutMapping("/{id}/update")
    public String updateUser(@PathVariable Long id, @RequestBody String userJson) {
        UserDto userDetails = JsonUtil.deserializeJson(userJson, UserDto.class);
        UserDto updatedUser = userService.updateUser(id, userDetails);
        return JsonUtil.serializeJson(Map.of("succeeded", updatedUser != null));
    }

    @GetMapping("/{id}/get")
    public String getUser(@PathVariable Long id) {
        UserDto user = userService.getUser(id);
        return user != null ? JsonUtil.serializeJson(user) : "{}";
    }
}