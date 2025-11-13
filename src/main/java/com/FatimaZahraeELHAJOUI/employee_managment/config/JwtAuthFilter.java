/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.config;

import com.FatimaZahraeELHAJOUI.employee_managment.services.UserDetailsImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author marwa
 */

@Component
public class JwtAuthFilter extends OncePerRequestFilter{
    @Autowired
    private JwtHelper jwtHelper;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        
        String path = request.getRequestURI();
       
        
        System.out.println("FILTER IS RUNING ...");
        String authSHeader = request.getHeader("Authorization");
        String token = null;
        
        if(authSHeader != null && authSHeader.startsWith("Bearer ")){
            token = authSHeader.substring(7);
        }
        
        // Skip JWT filter for auth endpoints
        if(token == null || path.startsWith("/auth/")){
            filterChain.doFilter(request, response);
            return;
        }
        
        String username = jwtHelper.extractUsername(token);
        
        if(username != null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            Boolean isTokenValid = jwtHelper.isTokenValid(token, userDetails);
            
            if(isTokenValid){
                var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}