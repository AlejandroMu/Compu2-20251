package co.icesi.taskManager.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.icesi.taskManager.config.TaskSocketHandler;
import co.icesi.taskManager.model.Task;
import co.icesi.taskManager.model.TaskList;
import co.icesi.taskManager.model.User;
import co.icesi.taskManager.repositories.TaskListRepository;
import co.icesi.taskManager.repositories.TaskRepository;
import co.icesi.taskManager.repositories.UserRepository;
import co.icesi.taskManager.services.interfaces.TaskService;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private TaskSocketHandler webSocketSessions;

    @Override
    public Task createTask(Task task, Integer listId) {
        TaskList taskList = taskListRepository.findById(listId).get();
        if (taskIsValid(task)) {
            task.setList(taskList);
            taskRepository.save(task);
            webSocketSessions.sendNotification("new Task");
            return task;
        }else{
            return null;
        }

    }

    private boolean taskIsValid(Task task) {
        return true;
    }

    @Override
    public Task updateTask(Task task) {
        Task t = taskRepository.findById(task.getId()).orElseThrow(() -> new RuntimeException("Task not found"));
        t.setDescription(task.getDescription());
        t.setName(task.getName());
        t.setNotes(task.getNotes());
        t.setPriority(task.getPriority());

        if (taskIsValid(t)) {
            taskRepository.save(t);
            return t;
        }else{
            return null;
        }
    }

    @Override
    public void deleteTask(long taskId) {
        Task t = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(t);
    }

    @Override
    public void assignTask(long taskId, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        List<User> assigns = task.getAssignedUsers();
        if (assigns == null) {
            assigns = new ArrayList<>();
        }
        assigns.add(user);
        task.setAssignedUsers(assigns);

        taskRepository.save(task);
    }

    @Override
    public void unassignTask(long taskId, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        List<User> assigns = task.getAssignedUsers();
        if (assigns == null) {
            assigns = new ArrayList<>();
        }
        assigns = assigns.stream().filter(u -> u.getId() != user.getId()).toList();
        task.setAssignedUsers(assigns);

        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        return task;

    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public List<Task> getTaskCompleted(String name){
        List<Task> tasks = taskRepository.findAll();
        List<Task> filt = tasks.stream().filter((t)->{
            return t.getName().equals(name);
        }).toList();

        return filt;
    }

}
