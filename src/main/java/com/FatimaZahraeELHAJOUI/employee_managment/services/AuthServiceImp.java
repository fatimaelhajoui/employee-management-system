/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.services;

import com.FatimaZahraeELHAJOUI.employee_managment.abstracts.AuthService;
import com.FatimaZahraeELHAJOUI.employee_managment.abstracts.EmailService;
import com.FatimaZahraeELHAJOUI.employee_managment.config.JwtHelper;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.LoginRequest;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.ResetPasswordRequest;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.SignupRequest;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.Employee;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.PasswordResetToken;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.UserAccount;
import com.FatimaZahraeELHAJOUI.employee_managment.repositories.EmployeeRepo;
import com.FatimaZahraeELHAJOUI.employee_managment.repositories.PasswordResetRepo;
import com.FatimaZahraeELHAJOUI.employee_managment.repositories.UserAccountRepo;
import com.FatimaZahraeELHAJOUI.employee_managment.shared.CustomResponseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marwa
 */
@Service
public class AuthServiceImp  implements AuthService{
    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private EmployeeRepo employeeRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtHelper jwtHelper;
     
    @Autowired
    private PasswordResetRepo passwordResetRepo;
    
    @Autowired
    private EmailService emailService;
    
    
    @Override
    @Transactional
    public void signup(SignupRequest signupRequest, String token) {
        try {
            // Find employee by token
            Employee employee = employeeRepo.findOneByAccountCreationToken(token)
                    .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                            "Invalid or expired token"
                    ));
            
            // Check if employee already has an account
            if (employee.isVerified()) {
                throw CustomResponseException.BadRequest("Account already exists for this employee");
            }
            
            // Check if username already exists
            if (userAccountRepo.findOneByUsername(signupRequest.getUsername()).isPresent()) {
                throw CustomResponseException.BadRequest("Username already exists");
            }
            
            // Update employee - mark as verified and clear token
            employee.setVerified(true);
            employee.setAccountCreationToken(null);
            employeeRepo.saveAndFlush(employee); // Use saveAndFlush to ensure immediate persistence
            
            // Create new user account
            UserAccount account = new UserAccount();
            account.setUsername(signupRequest.getUsername());
            account.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            account.setEmployee(employee);
            
            userAccountRepo.saveAndFlush(account); // Use saveAndFlush
        } catch (CustomResponseException ex) {
            throw CustomResponseException.BadRequest("Employee Creation Failed!");
        }
    }

    @Override
    public String login(LoginRequest loginRequest) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()      )
        );
        
         // Find user account
        UserAccount user = null;
        try {
            user = userAccountRepo.findOneByUsername(loginRequest.getUsername()).orElseThrow(()-> CustomResponseException.BadCredentials());
        } catch (CustomResponseException ex) {
            Logger.getLogger(AuthServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Generate JWT token with user ID
        Map<String, Object> customClaims = new HashMap();
        customClaims.put("userId", user.getId());
        
        return jwtHelper.generateToken(customClaims,user);
    }

    @Transactional
    @Override
    public void initiatePasswordRest(String username) {
        
        try{
            // Find user account
        UserAccount user = userAccountRepo.findOneByUsername(username).orElseThrow(()-> CustomResponseException.ResourceNotFound("user not found"));
        String token = UUID.randomUUID().toString();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(10);
        
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setExpiryDate(expiry);
        resetToken.setUser(user);
        
        passwordResetRepo.save(resetToken);
        
        emailService.sendPasswordResetEmail(user.getEmployee().getEmail(), token);
        
        }catch(Exception e){
            throw CustomResponseException.BadRequest("Sending reset password email fieled!");
        }
        
        
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        PasswordResetToken resetToken = passwordResetRepo.findOneByToken(resetPasswordRequest.getToken()).orElseThrow(()-> CustomResponseException.ResourceNotFound("token not found"));
        
        boolean isTokenExpired = resetToken.getExpiryDate().isBefore(LocalDateTime.now());
        if(isTokenExpired){
            passwordResetRepo.delete(resetToken);
            throw CustomResponseException.BadRequest("Token has expired! request a new one");
        }
        
        UserAccount user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        userAccountRepo.save(user);
        passwordResetRepo.delete(resetToken);
   
    }
    
}
