package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AppConfig {

    private JwtAuthenticationFilter tokenFilter;
    
    @Autowired
    public void setTokenFilter(JwtAuthenticationFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // @Bean
    // SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception {
    //     security.
    //         authorizeHttpRequests(aut -> 
    //             aut
    //                 .requestMatchers("/**.css", "/auth/registro","/products").permitAll()
    //                 .anyRequest().authenticated()
    //     ).formLogin(cus -> 
    //             cus
    //                 .loginPage("/auth/login")
    //                 .successForwardUrl("/auth/success")
    //                 .permitAll()
    //     ).logout(logout -> 
    //             logout
    //                 .logoutUrl("/auth/logout")
    //                 .logoutSuccessUrl("/auth/login")
    //                 .invalidateHttpSession(true)
    //                 .deleteCookies("JSESSIONID")
    //                 .permitAll()
    //     );
    //     return security.build();
    // }
    
    @Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(t -> t.disable())
            .csrf(c -> c.disable())
            .authorizeHttpRequests(requests -> requests
                            .requestMatchers("/public/login").permitAll()
                            .anyRequest().authenticated())
            .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
	}

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
