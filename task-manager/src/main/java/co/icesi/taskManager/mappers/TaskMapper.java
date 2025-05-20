package co.icesi.taskManager.mappers;

import org.mapstruct.Mapper;

import co.icesi.taskManager.dtos.TaskDto;
import co.icesi.taskManager.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    
    TaskDto taskToTaskDto(Task task);
    Task taskDtoToTask(TaskDto task);
}
