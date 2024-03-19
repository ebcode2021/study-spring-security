package com.example.chapter09.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthenticationLogginFilter implements Filter {

    private final Logger logger = Logger.getLogger(
            AuthenticationLogginFilter.class.getName()
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestId = httpRequest.getHeader("Request-Id");

        logger.info("인증 성공! request-id : " + requestId);

        chain.doFilter(request, response); // 요청을 필터 체인의 다음 필터에 전달
    }
}
