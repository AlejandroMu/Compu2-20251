package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.reposiories.RoleRepo;
import com.example.demo.services.interfaces.RoleService;

@Service
public class RoleServiceImp implements RoleService{
    
    @Autowired
    private RoleRepo roleRepo;


    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public Role getByName(String name) {
        return roleRepo.findByName(name);
    }
    
}
