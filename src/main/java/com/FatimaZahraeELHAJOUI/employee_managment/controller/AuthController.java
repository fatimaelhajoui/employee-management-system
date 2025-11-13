/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.controller;

import com.FatimaZahraeELHAJOUI.employee_managment.abstracts.AuthService;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.*;
import com.FatimaZahraeELHAJOUI.employee_managment.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author marwa
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    
    @PostMapping("/sign-up")
    public ResponseEntity<GlobalResponse<String>> signup(@Valid @RequestBody SignupRequest signupRequest, @RequestParam String token){
        
        authService.signup(signupRequest, token);
        return new ResponseEntity<>(new GlobalResponse <>("new Sign Up"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<String>> login(@Valid @RequestBody LoginRequest loginRequest){
        
        String token =authService.login(loginRequest);
        return new ResponseEntity<>(new GlobalResponse <>(token), HttpStatus.CREATED);
    }
    
    @PostMapping("/forgot-password/{username}")
     public ResponseEntity<GlobalResponse<String>> forgotPassword(@PathVariable String username){
        
        authService.initiatePasswordRest(username);
        return new ResponseEntity<>(new GlobalResponse <>(" Email sent! rest your password now"), HttpStatus.CREATED);
    }
     
    @PostMapping("/reset-password")
     public ResponseEntity<GlobalResponse<String>> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        
        authService.resetPassword(resetPasswordRequest);
        return new ResponseEntity<>(new GlobalResponse <>("password changed successfully!"), HttpStatus.CREATED);
    }
}
