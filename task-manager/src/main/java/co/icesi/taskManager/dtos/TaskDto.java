package co.icesi.taskManager.dtos;

import lombok.Data;

@Data
public class TaskDto {
    private String name, description, notes;
    private  Long priority,id;
}
