/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;
import lombok.*;

/**
 *
 * @author marwa
 */
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString 
public class Employee {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(name = "first_name", nullable = false, length = 100) 
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 100) 
    private String lastName;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "phone-number", length = 25) 
    private String phoneNumber;
    
    @Column(name = "hire-date", nullable = false, unique = false)
    private LocalDate hireDate;
    
    @Column(name = "position", nullable = false, length = 100)
    private String position ;
    
    @Column(name = "is_verified", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isVerified ;
    
    @Column(name = "account_creation_token")
    private String accountCreationToken ;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department", nullable = false)
    private Department department ;
    
    public String getDepartment(){
        return department.getName();
    }
}
