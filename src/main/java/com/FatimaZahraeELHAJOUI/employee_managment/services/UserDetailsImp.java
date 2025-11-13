/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.services;

import com.FatimaZahraeELHAJOUI.employee_managment.entities.UserAccount;
import com.FatimaZahraeELHAJOUI.employee_managment.repositories.UserAccountRepo;
import com.FatimaZahraeELHAJOUI.employee_managment.shared.CustomResponseException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author marwa
 */
@Service
public class UserDetailsImp implements UserDetailsService {

   @Autowired
   private UserAccountRepo userAccountRepo;
   
   
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> account = userAccountRepo.findOneByUsername(username);
        
        if(account.isEmpty()){
            
            try {
                throw CustomResponseException.BadCredentials();
            } catch (CustomResponseException ex) {
                Logger.getLogger(UserDetailsImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        UserAccount user = account.get();
        
        
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        
    }
    
}
