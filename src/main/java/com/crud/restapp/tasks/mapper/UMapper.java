package com.crud.restapp.tasks.mapper;

import com.crud.restapp.tasks.domain.Task;
import com.crud.restapp.tasks.domain.TaskDto;
import com.crud.restapp.tasks.domain.User;
import com.crud.restapp.tasks.domain.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface UMapper {
    UMapper INSTANCE = Mappers.getMapper(UMapper.class);

    @Mappings({
            @Mapping(source = "tasks", target = "tasksDto"),
            @Mapping(ignore = true, target = "password")})
    UserDto mapToUserDto(User user);

    @Mappings({
            @Mapping(source = "tasksDto", target = "tasks")})
    User mapToUser(UserDto userDto);

    @Mappings({@Mapping(ignore = true, target = "user")})
    Task mapToTask(TaskDto taskDto);


    @Mappings({@Mapping(ignore = true, target = "userDto")})
    TaskDto mapToTaskDto(Task task);

    List<UserDto> mapToUserDtoList(List<User> users);


}
