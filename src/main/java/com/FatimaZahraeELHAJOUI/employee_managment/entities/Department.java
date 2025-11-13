/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.*;

/**
 *
 * @author marwa
 */
@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(name = "name", nullable = false, unique = true, length = 100) 
    private String name; 
}
