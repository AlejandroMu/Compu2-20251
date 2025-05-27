package co.icesi.taskManager.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    private String notes;
    private Long priority;

    @ManyToOne
    @JoinColumn(name = "list")
    @JsonIgnore
    private TaskList list;

    @ManyToMany(mappedBy = "tasks", cascade = CascadeType.ALL)
    private List<User> assignedUsers;



}
