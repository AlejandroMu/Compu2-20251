package co.icesi.taskManager.controllers.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.icesi.taskManager.dtos.TaskDto;

@RequestMapping("/api/tasks")
public interface TaskController {
    
    @GetMapping
    public ResponseEntity<List<TaskDto>> findAllTask(Authentication auth);

    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody TaskDto dto);

    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody TaskDto dto);

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable long id);

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id);

}
