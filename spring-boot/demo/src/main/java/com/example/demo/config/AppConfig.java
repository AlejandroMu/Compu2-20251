package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AppConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http.formLogin( l-> l
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/products", true)
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
        );
        return http.build();
    }
    
}
