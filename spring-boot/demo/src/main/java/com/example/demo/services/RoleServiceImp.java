package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.reposiories.RoleRepo;
import com.example.demo.services.interfaces.RoleService;

import jakarta.annotation.PostConstruct;

@Service
public class RoleServiceImp implements RoleService{
    
    @Autowired
    private RoleRepo roleRepo;

    @PostConstruct
    public void init(){
        roleRepo.saveAll(List.of(
            Role.builder().name("ROLE_USER").build(),
            Role.builder().name("ROLE_ADMIN").build()
        ));
    }

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public Role getByName(String name) {
        return roleRepo.findByName(name);
    }
    
}
