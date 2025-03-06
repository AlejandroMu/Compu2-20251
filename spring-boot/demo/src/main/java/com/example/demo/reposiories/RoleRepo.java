package com.example.demo.reposiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    
    public Role findByName(String name);
}
