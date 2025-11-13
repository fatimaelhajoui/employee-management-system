/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.repositories;

import com.FatimaZahraeELHAJOUI.employee_managment.entities.*;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author marwa
 */
public interface UserAccountRepo extends JpaRepository<UserAccount, UUID>{
    Optional<UserAccount> findOneByUsername(String username);
 
    @Query("""
      SELECT COUNT(u) > 0 FROM UserAccount u
      WHERE u.username = :username AND u.employee.id = :employeedId
      """)
    public boolean isOwner(@Param("username") String username, @Param("employeedId") UUID employeeId);
}
