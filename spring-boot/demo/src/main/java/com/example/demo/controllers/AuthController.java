package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.LoginDTO;
import com.example.demo.dtos.LoginInDTO;
import com.example.demo.model.User;
import com.example.demo.services.impl.JwtService;
import com.example.demo.services.impl.UserServiceImpl;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/public")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginInDTO loginInDTO){

        
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginInDTO.getUsername(), loginInDTO.getPassword())
        );

        String token = jwtService.generateToken(authentication);
        
        LoginDTO ret = new LoginDTO();
        ret.setToken(token);

        return ResponseEntity.ok(ret);
    }
    
    
}
