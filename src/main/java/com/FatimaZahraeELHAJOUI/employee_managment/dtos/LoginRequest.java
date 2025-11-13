/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 *
 * @author marwa
 */
@Getter
public class LoginRequest {
     @NotNull(message="username is required")
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    private String username;
    
    @NotNull(message="password is required")
    @Size(min = 2, max = 50, message = "password must be between 2 and 50 characters")
    private String password;
}
