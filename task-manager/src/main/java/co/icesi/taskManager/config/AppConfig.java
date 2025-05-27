package co.icesi.taskManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import co.icesi.taskManager.utils.JwtFilter;
import co.icesi.taskManager.utils.JwtService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class AppConfig {

    @Autowired
    private JwtService jwtService;

    JwtFilter getFilter() {
        JwtFilter jwtFilter = new JwtFilter();
        jwtFilter.setJwtService(jwtService);
        return jwtFilter;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/api/**")
                .cors(t -> t.configurationSource(corsConfigurationSource()))
                .csrf(c -> c.disable())
                .authorizeHttpRequests(requests -> requests                        .anyRequest().authenticated())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .formLogin(f -> f.disable())
                .sessionManagement(t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(getFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    SecurityFilterChain securityFilterChainMvc(HttpSecurity security) throws Exception {
        security
                .securityMatcher("/**")
                .authorizeHttpRequests(aut -> aut
                        .requestMatchers("/**.css", "/products",  "/h2-console/**", "/error").permitAll()
                        .anyRequest().authenticated())
                .formLogin(cus -> cus
                        .loginPage("/auth/login")
                        .successForwardUrl("/auth/success")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());
        return security.build();
    }

    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
