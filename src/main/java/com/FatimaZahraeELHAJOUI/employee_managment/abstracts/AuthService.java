/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.abstracts;

import com.FatimaZahraeELHAJOUI.employee_managment.dtos.LoginRequest;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.ResetPasswordRequest;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.SignupRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author marwa
 */
public interface AuthService {
    void signup(SignupRequest signupRequest, String token);
    String login(LoginRequest loginRequest);
    void initiatePasswordRest(String username);
    void resetPassword(ResetPasswordRequest resetPasswordRequest);
}
