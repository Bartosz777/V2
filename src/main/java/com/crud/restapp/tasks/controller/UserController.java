package com.crud.restapp.tasks.controller;

import com.crud.restapp.tasks.domain.UserDto;
import com.crud.restapp.tasks.mapper.UserMapper;
import com.crud.restapp.tasks.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserService uService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(uService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public UserDto getUser(@PathVariable(name = "userId") Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(uService.getUserById(userId).orElseThrow(
                () -> new UserNotFoundException("User: " + userId + " doesn't exist")));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public void deleteUser(@PathVariable(name = "userId") Long userId) {
        try {
            uService.deleteUser(userId);
        } catch (UserNotFoundException e) {
            LoggerFactory.getLogger(UserController.class).warn(e.getMessage());
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(uService.saveUser(userMapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        uService.saveUser(userMapper.mapToUser(userDto));
    }
}
