package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.reposiories.UserRepo;
import com.example.demo.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username, null));
        List<UserRole> roles = user.getRoles();

        List<GrantedAuthority> auths = new ArrayList<>();
        if(roles != null && !roles.isEmpty()) {
            auths = roles.stream()
                    .map(userRole -> (GrantedAuthority)() -> userRole.getRole().getName())
                    .toList();
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(auths)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

        return userDetails;
    }

    public boolean saveUser(User user) {
        if(userRepo.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepo.save(user);

        return true;
    }

    @Override
    public User findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }
    
}
