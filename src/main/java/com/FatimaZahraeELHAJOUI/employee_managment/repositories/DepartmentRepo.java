/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.repositories;

import com.FatimaZahraeELHAJOUI.employee_managment.entities.Department;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marwa
 */
public interface DepartmentRepo extends JpaRepository<Department, UUID>{
    
}
