package co.icesi.taskManager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.taskManager.controllers.api.TaskController;
import co.icesi.taskManager.dtos.TaskDto;
import co.icesi.taskManager.mappers.TaskMapper;
import co.icesi.taskManager.model.Task;
import co.icesi.taskManager.services.interfaces.TaskService;

@RestController
public class TaskControllerImpl implements TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper mapper;

    @Override
    public ResponseEntity<List<TaskDto>> findAllTask(Authentication auth) {
        try {
            List<Task> tasks = taskService.getAllTask();
            List<TaskDto> taskDtos = tasks.stream().map(mapper::taskToTaskDto).toList();
            return ResponseEntity.ok(taskDtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_TASK')")
    public ResponseEntity<?> addTask(TaskDto dto) {
        Task task = mapper.taskDtoToTask(dto);
        task = taskService.createTask(task);
        if (task == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(mapper.taskToTaskDto(task));
    }

    @Override
    public ResponseEntity<?> updateTask(TaskDto dto) {
        Task task = mapper.taskDtoToTask(dto);
        task = taskService.updateTask(task);
        if (task == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(200).body(mapper.taskToTaskDto(task));
    }

    @Override
    public ResponseEntity<?> deleteTask(long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> findById(long id) {
        try {
            Task task = taskService.getTaskById(id);
            TaskDto dto = mapper.taskToTaskDto(task);
            return ResponseEntity.ok().body(dto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

}
