package com.example.chapter10.filter;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;

import java.io.IOException;

public class CsrfTokenLogger implements Filter {

    private Logger logger = LoggerFactory.getLogger(CsrfTokenLogger.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object object = request.getAttribute("_csrf");
        CsrfToken csrfToken = (CsrfToken) object;

        logger.info("CSRF token : " + csrfToken.getToken());

        chain.doFilter(request, response);
    }
}
