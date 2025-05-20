package co.icesi.taskManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.icesi.taskManager.model.User;
import co.icesi.taskManager.services.impl.UserServiceImp;
import co.icesi.taskManager.utils.JwtService;


@RestController
public class HomeController {
    
    @Autowired
    private UserServiceImp userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> home(@RequestBody User user) {

        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        if (userDetails == null) {
            return ResponseEntity.badRequest().body("Invalid user");
        }
        if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid user");
        }   
        String token = jwtService.generateToken(
            new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities()));

        return ResponseEntity.ok(token);
    }
}