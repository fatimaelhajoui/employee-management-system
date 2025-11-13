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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.util.UUID;
import lombok.*;
/**
 *
 * @author marwa
 */
@Entity
@Table(name = "leave_request")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class LeaveRequest {
     @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(name = "start_date", nullable = false) 
    private Date startDate; 
    
    @Column(name = "end_date", nullable = false) 
    private Date endDate; 
    
    @Column(name = "reason", nullable = false) 
    private String reason; 
    
    @Column(name = "status", nullable = false) 
    private String status;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee ;
    
    public UUID getEmployee(){
        return employee.getId();
    }
    
}
