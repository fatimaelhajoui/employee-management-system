/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;

/**
 *
 * @author marwa
 */

@Getter
public class EmployeeUpdate {
     @NotNull(message="first name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String firstName;
    
    @NotNull(message="last name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String lastName;
    
    @NotNull(message="phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message="invalid phone number format")
    private String phoneNumber;
    
    @NotNull(message="hireDate is required")
    @PastOrPresent(message="hireDate cannot be in the future")
    private LocalDate hireDate;
    
    @NotNull(message="position is required")
    private String position ;
}
