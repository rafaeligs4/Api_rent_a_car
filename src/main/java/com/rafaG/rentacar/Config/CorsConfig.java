package com.rafaG.rentacar.Config;

import com.rafaG.rentacar.Constants.Constants;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements Constants{
    @Bean
    WebMvcConfigurer corsConfigutations(){
        return  new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/login")
                        .allowedOrigins(LOCALHOST_NG)
                        .allowedMethods("*")
                        .exposedHeaders("*");


                registry
                        .addMapping("/api/**")
                        .allowedOrigins(LOCALHOST_NG)
                        .allowedMethods("*")
                ;

            }
        };
    }
}
