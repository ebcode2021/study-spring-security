package com.example.chapter09.config;

import com.example.chapter09.filter.AuthenticationLogginFilter;
import com.example.chapter09.filter.RequestValidationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore( // 필터 체인에서 인증 필터 앞에 맞춤형 필터의 인스턴스 추가
                new RequestValidationFilter(),
                BasicAuthenticationFilter.class
            )
            .addFilterAfter(
                new AuthenticationLogginFilter(),
                BasicAuthenticationFilter.class
            )
            .authorizeHttpRequests(
                (authz) -> authz.anyRequest().permitAll()
            );
        return http.build();
    }
}