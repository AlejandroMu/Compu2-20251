package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class UserRoleKey {
    
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "role_id")
    private Long roleId;
}
