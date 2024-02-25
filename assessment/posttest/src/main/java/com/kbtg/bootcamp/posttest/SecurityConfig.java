package com.kbtg.bootcamp.posttest;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{

        http.csrf((csrf)-> csrf.disable()).
                authorizeHttpRequests((request)->request
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/lotteries/**").permitAll()
                        .requestMatchers("/admin/**").authenticated()
                );
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
