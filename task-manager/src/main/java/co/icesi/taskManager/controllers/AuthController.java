package co.icesi.taskManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.icesi.taskManager.model.User;
import co.icesi.taskManager.services.impl.UserServiceImp;
import co.icesi.taskManager.utils.JwtService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServiceImp userServiceImp;
    
    @GetMapping("/login")
    public String loginTemplate(){
        return "auth/login.html";
    }

    @PostMapping("/success")
    public String afterLogin(Authentication auth, Model model) {
        String token = jwtService.generateToken(auth);
        return "redirect:http://localhost:5173/tasks-board?token="+token;
    }

    @GetMapping("/registro")
    public String registri(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "auth/registro.html";
    }

    @PostMapping("/registro")
    public String saveUser(@ModelAttribute User user) {
        System.out.println(user);
        if(userServiceImp.saveUser(user)) {
            return "redirect:/auth/login";
        } else {
            return "redirect:/auth/registro?error=true";
        }

    }
    
    
}
