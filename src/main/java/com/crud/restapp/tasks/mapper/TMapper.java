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

@Mapper
public interface TMapper {
    TMapper INSTANCE = Mappers.getMapper(TMapper.class);

    @Mappings({@Mapping(source = "user", target = "userDto")})
    TaskDto mapToTaskDto(Task task);

    @Mappings({@Mapping(source = "userDto", target = "user")})
    Task mapToTask(TaskDto taskDto);

    @Mappings({
            @Mapping(ignore = true, target = "tasks"),
            @Mapping(ignore = true, target = "authorities")})
    User mapToUser(UserDto userDto);

    @Mappings({
            @Mapping(ignore = true, target = "tasksDto"),
            @Mapping(ignore = true, target = "password")
    })
    UserDto mapToUserDto(User user);


    List<TaskDto> mapToTaskDtoList(List<Task> taskList);


}
