/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.services;

import com.FatimaZahraeELHAJOUI.employee_managment.abstracts.LeaveRequestService;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.LeaveRequestCreate;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.*;
import com.FatimaZahraeELHAJOUI.employee_managment.repositories.*;
import com.FatimaZahraeELHAJOUI.employee_managment.shared.CustomResponseException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 *
 * @author marwa
 */
@Service
public class LeaveRequestImp implements LeaveRequestService{

    @Autowired
    private EmployeeRepo employeeRepo;
    
    @Autowired
    private LeaveRequestRepo leaveRequestRepo;
    
    
    @Override
    @PreAuthorize("@securityUtils.isOwner(#employeeId)")
    public LeaveRequest createOne(LeaveRequestCreate leaveRequestCreate, UUID employeeId) {
        Employee employee = null;
       
        try {
            employee = employeeRepo.findById(employeeId)
                    .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                            "Department with id " + employeeId + " not found"
                    ));
        } catch (CustomResponseException ex) {
            Logger.getLogger(LeaveRequestImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        LeaveRequest leaveRequest = new LeaveRequest();
        
        leaveRequest.setStatus("Pending");
        leaveRequest.setReason(leaveRequestCreate.getReason());
        leaveRequest.setStartDate(leaveRequestCreate.getStartDate());
        leaveRequest.setEndDate(leaveRequestCreate.getEndDate());
        leaveRequest.setEmployee(employee);
        
        
        leaveRequestRepo.save(leaveRequest);
        return leaveRequest;
        
        
    }

    @Override
    public List<LeaveRequest> findAllByEmployeeId(UUID employeeId) {
        
        return leaveRequestRepo.findAllByEmployeeId(employeeId);
    }
    
}
