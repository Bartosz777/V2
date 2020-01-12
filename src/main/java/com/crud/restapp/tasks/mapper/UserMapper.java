package com.crud.restapp.tasks.mapper;


import com.crud.restapp.tasks.domain.User;
import com.crud.restapp.tasks.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class UserMapper {

    @Autowired
    private TaskMapper taskMapper;

    public User mapToUser(final UserDto userDto) {
        return new User(userDto.getId(), userDto.getUsername(), getPasswordEncoder().encode(userDto.getPassword()), userDto.getRole(),
                taskMapper.mapToTaskList(userDto.getTasksDto()));
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(),
                taskMapper.mapToTaskDtoList(user.getTasks()), user.getRole());
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getUsername(), null,
                        taskMapper.mapToTaskDtoList(user.getTasks()), user.getRole()))
                .collect(Collectors.toList());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
