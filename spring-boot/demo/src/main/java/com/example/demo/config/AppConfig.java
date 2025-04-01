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
    SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception {
        security.
            authorizeHttpRequests(aut -> 
                aut
                    .requestMatchers("/**.css", "/auth/registro").permitAll()
                    .anyRequest().authenticated()
        ).formLogin(cus -> 
                cus
                    .loginPage("/auth/login")
                    // .successForwardUrl("/auth/success")
                    .permitAll()
        ).logout(logout -> 
                logout
                    .logoutUrl("/auth/logout")
                    .logoutSuccessUrl("/auth/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
        );
        return security.build();
    }
    
}
