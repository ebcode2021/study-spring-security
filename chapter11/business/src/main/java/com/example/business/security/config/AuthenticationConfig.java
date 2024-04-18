package com.example.business.security.config;

import com.example.business.security.auth.OtpAuthenticationProvider;
import com.example.business.security.auth.UsernamePasswordAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final OtpAuthenticationProvider otpAuthenticationProvider;

    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(
                List.of(otpAuthenticationProvider, usernamePasswordAuthenticationProvider)
        );
    }
}
