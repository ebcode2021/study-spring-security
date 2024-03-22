package com.example.chapter10.config;

import com.example.chapter10.filter.CsrfTokenLogger;
import com.example.chapter10.repository.CustomCsrfTokenRepository;
import com.example.chapter10.repository.JpaTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
@AllArgsConstructor
public class FilterConfig {

    private final JpaTokenRepository jpaTokenRepository;

    @Bean
    public CsrfTokenRepository csrfTokenRepository(){
        return new CustomCsrfTokenRepository(jpaTokenRepository);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
            .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
            .csrf(c -> {
                c.csrfTokenRepository(csrfTokenRepository());
                // 인코딩되지 않는 경우(요청 헤더 같은)에는 CsrfTokenRequestAttributeHandler()를 사용하여 해결해야함!
                c.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler());
                c. ignoringRequestMatchers("/ciao");
            })
            .formLogin(f -> f.defaultSuccessUrl("/main", true));


        return http.build();
    }

}
