package co.icesi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
    
}
