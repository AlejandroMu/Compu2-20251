package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.services.impl.UserServiceImpl;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl userService;
    
    @GetMapping("/login")
    public String loginTemplate(){
        return "auth/login.html";
    }

    @PostMapping("/success")
    public String afterLogin(Authentication auth, Model model) {
        
        return "redirect:/products";
    }

    @GetMapping("/registro")
    public String registri(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "auth/registro.html";
    }

    @PostMapping("/registro")
    public String saveUser(@ModelAttribute @Valid User user) {
        System.out.println(user);
        if(userService.saveUser(user)) {
            return "redirect:/auth/login";
        } else {
            return "redirect:/auth/registro?error=true";
        }

    }
    
    
}
