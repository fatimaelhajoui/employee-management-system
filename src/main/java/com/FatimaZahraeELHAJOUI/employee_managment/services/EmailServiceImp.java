/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.services;

import com.FatimaZahraeELHAJOUI.employee_managment.abstracts.EmailService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author marwa
 */
@Service
public class EmailServiceImp implements EmailService{
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${backend.origin}")
    private String ORIGIN;
    
    @Value("${spring.mail.username}")
    private String FROM;

    @Override
    public void sendAccountCreationEmail(String to, String token) {
        String link = ORIGIN +"/auth/sign-up?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM);
        message.setTo(to);
        message.setSubject("Create Your Account");
        message.setText("Hi! Please create your account using this link \n: " + link);
        mailSender.send(message);
        
        
    }
    
    
     @Override
    public void sendPasswordResetEmail(String to, String token) {
        String link = ORIGIN +"/auth/reset-password?token=" + token;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM);
        message.setTo(to);
        message.setSubject("Reset Your Paasword");
        message.setText("Hi! Please rest your password using this link \n: " + link);
        mailSender.send(message);
        
        
    }
    
}
