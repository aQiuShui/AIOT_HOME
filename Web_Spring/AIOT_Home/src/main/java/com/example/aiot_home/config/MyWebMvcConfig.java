package com.example.aiot_home.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(1800)
                .allowedOriginPatterns("*");
    }
}
