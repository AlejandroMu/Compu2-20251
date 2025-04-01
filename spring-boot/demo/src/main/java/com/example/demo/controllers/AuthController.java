package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;



@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @GetMapping("/login")
    public String loginTemplate(){
        return "auth/login.html";
    }

    @GetMapping("/registro")
    public String registri() {
        return "auth/registro.html";
    }

    @PostMapping("/registro")
    public String saveUser(@ModelAttribute User user) {
        System.out.println(user);
        return "redirect:/auth/login";
    }
    
    
}
