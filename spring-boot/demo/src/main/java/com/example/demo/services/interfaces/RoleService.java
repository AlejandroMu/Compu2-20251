package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.model.Role;

public interface RoleService {

    public List<Role> findAll();

    public Role getByName(String name);
    
}
