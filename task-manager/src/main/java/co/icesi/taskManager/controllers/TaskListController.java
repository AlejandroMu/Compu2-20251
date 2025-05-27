package co.icesi.taskManager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.taskManager.model.TaskList;
import co.icesi.taskManager.services.impl.TaskListService;


@RestController
@RequestMapping("/api/tasks-list")
public class TaskListController {
    @Autowired
    private TaskListService service;

    @GetMapping
    public List<TaskList> getMethodName() {
        return service.getAll();
    }
    
}
