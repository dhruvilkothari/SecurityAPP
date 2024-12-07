package com.example.SecurityApp.SecurityApp.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfigs {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(Customizer.withDefaults()).csrf().disable()
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.anyRequest().authenticated();
                });
        return httpSecurity.build();
    }
}
