package co.icesi.taskManager.services.interfaces;

import java.util.List;

import co.icesi.taskManager.model.Task;

public interface TaskService {
    Task createTask(Task task);
    Task updateTask(Task task);
    void deleteTask(long taskId);
    void assignTask(long taskId, long userId);
    void unassignTask(long taskId, long userId);
    Task getTaskById(long taskId);
    List<Task> getAllTask();
}
