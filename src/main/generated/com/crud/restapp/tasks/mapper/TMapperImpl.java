package com.crud.restapp.tasks.mapper;

import com.crud.restapp.tasks.domain.Task;
import com.crud.restapp.tasks.domain.TaskDto;
import com.crud.restapp.tasks.domain.User;
import com.crud.restapp.tasks.domain.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-01-18T20:17:50+0100",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
public class TMapperImpl implements TMapper {

    @Override
    public TaskDto mapToTaskDto(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDto taskDto = new TaskDto();

        taskDto.setUserDto( mapToUserDto( task.getUser() ) );
        taskDto.setId( task.getId() );
        taskDto.setTitle( task.getTitle() );
        taskDto.setContent( task.getContent() );
        taskDto.setStartDate( task.getStartDate() );

        return taskDto;
    }

    @Override
    public Task mapToTask(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        Task task = new Task();

        task.setUser( mapToUser( taskDto.getUserDto() ) );
        task.setId( taskDto.getId() );
        task.setTitle( taskDto.getTitle() );
        task.setContent( taskDto.getContent() );
        task.setStartDate( taskDto.getStartDate() );

        return task;
    }

    @Override
    public User mapToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setRole( userDto.getRole() );
        user.setId( userDto.getId() );
        user.setUsername( userDto.getUsername() );
        user.setPassword( userDto.getPassword() );

        return user;
    }

    @Override
    public UserDto mapToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setRole( user.getRole() );
        userDto.setId( user.getId() );
        userDto.setUsername( user.getUsername() );

        return userDto;
    }

    @Override
    public List<TaskDto> mapToTaskDtoList(List<Task> taskList) {
        if ( taskList == null ) {
            return null;
        }

        List<TaskDto> list = new ArrayList<TaskDto>( taskList.size() );
        for ( Task task : taskList ) {
            list.add( mapToTaskDto( task ) );
        }

        return list;
    }
}
