/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.abstracts;

import com.FatimaZahraeELHAJOUI.employee_managment.dtos.LeaveRequestCreate;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.LeaveRequest;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author marwa
 */
public interface LeaveRequestService {
    LeaveRequest createOne(LeaveRequestCreate leaveRequestCreate, UUID employeeId);
    List<LeaveRequest> findAllByEmployeeId(UUID employeeId);
}
