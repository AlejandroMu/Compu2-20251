package co.icesi.taskManager.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.icesi.taskManager.model.TaskList;
import co.icesi.taskManager.repositories.TaskListRepository;

@Service
public class TaskListService {
    
    @Autowired
    private TaskListRepository repository;


    public List<TaskList> getAll(){
        return repository.findAll();
    }

}
