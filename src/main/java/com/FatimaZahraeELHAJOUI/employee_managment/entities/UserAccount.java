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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author marwa
 */
@Entity
@Table(name = "user-account")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class UserAccount implements UserDetails{
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(name = "username", nullable = false, unique = true, length = 100) 
    private String username;
    
    @Column(name = "password", nullable = false, length = 100) 
    private String password;
    
    @Column(name = "role", nullable = false, length = 100) 
    private String role= "USER";
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
