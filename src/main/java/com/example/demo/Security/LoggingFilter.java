package com.example.demo.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException {

        System.out.println("📦 Incoming Request:");
        System.out.println("➡️ Method: " + request.getMethod());
        System.out.println("➡️ URI: " + request.getRequestURI());
        System.out.println("➡️ Content-Type: " + request.getContentType());

        filterChain.doFilter(request, response);
    }
}