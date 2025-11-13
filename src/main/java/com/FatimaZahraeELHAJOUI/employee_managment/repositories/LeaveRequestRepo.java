/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.repositories;

import com.FatimaZahraeELHAJOUI.employee_managment.entities.*;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marwa
 */
public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, UUID>{

    List<LeaveRequest> findAllByEmployeeId(UUID employeeId);
    
}
