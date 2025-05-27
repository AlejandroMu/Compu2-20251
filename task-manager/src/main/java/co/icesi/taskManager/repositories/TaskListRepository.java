package co.icesi.taskManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.icesi.taskManager.model.TaskList;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Integer>{
    
}
