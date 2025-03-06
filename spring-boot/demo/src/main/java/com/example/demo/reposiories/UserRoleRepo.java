package com.example.demo.reposiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserRole;
import com.example.demo.model.UserRoleKey;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, UserRoleKey> {
    
}
