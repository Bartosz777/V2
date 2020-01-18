package com.crud.restapp.tasks.mapper;

import com.crud.restapp.tasks.domain.Task;
import com.crud.restapp.tasks.domain.TaskDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TaskMapper {
    public Task mapToTask(final TaskDto taskDto) {
        return TMapper.INSTANCE.mapToTask(taskDto);
    }

    public TaskDto mapToTaskDto(final Task task) {
        return TMapper.INSTANCE.mapToTaskDto(task);
    }

    public List<TaskDto> mapToTaskDtoList(final List<Task> taskList) {
        return TMapper.INSTANCE.mapToTaskDtoList(taskList);
    }
}
