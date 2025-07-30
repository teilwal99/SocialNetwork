package com.example.demo.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    public JwtAuthFilter(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);
            if (username != null && jwtUtil.validateToken(jwt)) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null, null);
                SecurityContextHolder.getContext().setAuthentication(auth);

                //set the current user in the request context
                User user = userService.findByUsername(username);

                // Set it in the request so controllers can access it
                request.setAttribute("currentUser", user);
            }
        }
        filterChain.doFilter(request, response);
    }
}