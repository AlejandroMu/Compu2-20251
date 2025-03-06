package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.model.UserRoleKey;
import com.example.demo.reposiories.UserRepo;
import com.example.demo.services.interfaces.RoleService;

import jakarta.transaction.Transactional;


@RestController
public class Controller {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleService roleService;
    
    @GetMapping("/")
    @Transactional(rollbackOn = {Exception.class})
    public ResponseEntity<?> getMethodName() {
        Role role = roleService.getByName("ROLE_USER");
        User user =   User.builder()
            .name("John")
            .lastname("Doe").build();
        userRepo.save(user);
        UserRoleKey key = new UserRoleKey();
        key.setRoleId(role.getId());
        key.setUserId(user.getId());
        UserRole userRole = UserRole.builder()
            .user(user)
            .role(role).build();
        userRole.setId(key);
        List<UserRole> roles = user.getRoles();
        // if(roles == null){
        //     roles = new ArrayList<>();
        // }
        roles.add(userRole);
        user.setRoles(roles);
        userRepo.save(user);
        return ResponseEntity.ok(userRepo.findAll());
    }
    
}
