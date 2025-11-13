/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.utils;

import com.FatimaZahraeELHAJOUI.employee_managment.repositories.UserAccountRepo;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author marwa
 */
@Component
public class SecurityUtils {
    @Autowired
    private UserAccountRepo userAccountRepo;
    
    public boolean isOwner(UUID incomingemployeeId){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getUsername());
        System.out.println("HERE " + incomingemployeeId);
        return userAccountRepo.isOwner(userDetails.getUsername(), incomingemployeeId);
    }
}
