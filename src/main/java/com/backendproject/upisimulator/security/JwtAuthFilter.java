package com.backendproject.upisimulator.security;

import org.springframework.web.filter.OncePerRequestFilter;

import com.backendproject.upisimulator.service.JwtService;
import com.backendproject.upisimulator.service.MyUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter
{
    private final JwtService jwtService;
    private final MyUserDetailService myUserDetailService;
    public JwtAuthFilter(JwtService jwtService,MyUserDetailService myUserDetailService)
    {
        this.jwtService=jwtService;
        this.myUserDetailService=myUserDetailService;
    } 
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
    {
        String authHeader = request.getHeader("Authorization");
        if(authHeader!=null && authHeader.startsWith("Bearer"))
        {
            String token = authHeader.substring(7);
            String email = jwtService.validateToken(token);
            myUserDetailService.fetchUser(email);
        }
    }
}
