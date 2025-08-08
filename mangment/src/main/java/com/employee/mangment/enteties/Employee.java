/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employee.mangment.enteties;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author marwa
 */
@Entity
@Table(name="EMPLOYEES")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class Employee {


    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String fullname; 
    
    @NotEmpty(message = "CIN is required")
    private String cin;
    
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;
    
    @NotEmpty(message = "Phone is required")
    private String phone;
    
    @NotNull(message = "Hire date is required")
    @Temporal(TemporalType.DATE)
    private Date hiredate;
    
    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    private int salary; 
    private boolean active;
            

}
