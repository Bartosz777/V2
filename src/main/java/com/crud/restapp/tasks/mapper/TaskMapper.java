package com.crud.restapp.tasks.mapper;

import com.crud.restapp.tasks.domain.Task;
import com.crud.restapp.tasks.domain.TaskDto;
import com.crud.restapp.tasks.domain.User;
import com.crud.restapp.tasks.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    @Autowired
    private UserMapper userMapper;

   public TaskDto mapToTaskDto(final Task task) {
       return new TaskDto(task.getId(), task.getTitle(), task.getContent(),
               userMapper.mapToUserDto(task.getUser()), task.getStartDate());
   }


    public Task mapToTask(final TaskDto taskDto) {
        return new Task(taskDto.getId(), taskDto.getTitle(), taskDto.getContent(),
                userMapper.mapToUser(taskDto.getUserDto()), taskDto.getStartDate());
    }

    public List<TaskDto> mapToTaskDtoList(List<Task> tasks) {
        return tasks.stream()
                .map(task  -> new TaskDto(task.getId(), task.getTitle(), task.getContent(),
                        new UserDto(task.getUser().getId(), task.getUser().getUsername(), null,
                                null, task.getUser().getRole()), task.getStartDate()))
                .collect(Collectors.toList());
    }

    public List<Task> mapToTaskList(List<TaskDto> tasksDto) {
        return tasksDto.stream()
                .map(task  -> new Task(task.getId(), task.getTitle(), task.getContent(),
                        new User(task.getUserDto().getId(), task.getUserDto().getUsername(), task.getUserDto().getPassword(),
                                task.getUserDto().getRole(), null), task.getStartDate()))
                .collect(Collectors.toList());
    }
}

