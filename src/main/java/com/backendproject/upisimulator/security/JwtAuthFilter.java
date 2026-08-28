package com.backendproject.upisimulator.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.backendproject.upisimulator.service.JwtService;
import com.backendproject.upisimulator.service.MyUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter
{
    private final JwtService jwtService;
    private final MyUserDetailService myUserDetailService;
    public JwtAuthFilter(JwtService jwtService,MyUserDetailService myUserDetailService)
    {
        this.jwtService=jwtService;
        this.myUserDetailService=myUserDetailService;
    } 
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)throws IOException,ServletException
    {
        String authHeader = request.getHeader("Authorization");
        if(authHeader!=null && authHeader.startsWith("Bearer "))
        {
            String token = authHeader.substring(7);
            String email = jwtService.validateToken(token);
            MyUserDetail userDetail = myUserDetailService.fetchUser(email);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication); 
        }
        filterChain.doFilter(request, response);
    }
}
