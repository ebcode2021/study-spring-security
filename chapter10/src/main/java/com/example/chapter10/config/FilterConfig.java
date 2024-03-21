package com.example.chapter10.config;

import com.example.chapter10.filter.CsrfTokenLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class FilterConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
            .authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
            .formLogin(f -> f.defaultSuccessUrl("/main", true));

        return http.build();
    }
}
