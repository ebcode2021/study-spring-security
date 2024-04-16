package com.example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf 비활성화
        http.csrf(AbstractHttpConfigurer::disable);

        // 인증 없이 모든 호출 허용
        http.authorizeHttpRequests(authz -> authz.anyRequest().permitAll());

        // iframe을 허용하여 h2 화면을 볼 수 있도록 세팅
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }
}
