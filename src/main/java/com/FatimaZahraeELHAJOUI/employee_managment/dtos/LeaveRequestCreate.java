/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;
import lombok.Getter;

/**
 *
 * @author marwa
 */

@Getter
public class LeaveRequestCreate {
    
    @NotNull(message="StartDate is required")
    @FutureOrPresent(message="StartDate cannot be in the past")
    private Date startDate;
    
    @NotNull(message="endDate is required")
    @FutureOrPresent(message="endDate cannot be in the past")
    private Date endDate;
    
    @NotNull(message="reason is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private String reason;
    
}
