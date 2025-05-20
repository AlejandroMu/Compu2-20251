package co.icesi.taskManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.icesi.taskManager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    
}
