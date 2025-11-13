/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.repositories;

import com.FatimaZahraeELHAJOUI.employee_managment.entities.Employee;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marwa
 */
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID>{
    Optional<Employee> findOneByAccountCreationToken(String token);
}
