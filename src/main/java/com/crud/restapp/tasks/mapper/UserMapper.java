package com.crud.restapp.tasks.mapper;

import com.crud.restapp.tasks.domain.User;
import com.crud.restapp.tasks.domain.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        userDto.setPassword(getPasswordEncoder().encode(userDto.getPassword()));
        return UMapper.INSTANCE.mapToUser(userDto);
    }

    public UserDto mapToUserDto(final User user) {
        return UMapper.INSTANCE.mapToUserDto(user);
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return UMapper.INSTANCE.mapToUserDtoList(users);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
