/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.controller;

import com.FatimaZahraeELHAJOUI.employee_managment.abstracts.EmployeeService;
import com.FatimaZahraeELHAJOUI.employee_managment.abstracts.LeaveRequestService;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.*;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.*;
import com.FatimaZahraeELHAJOUI.employee_managment.repositories.LeaveRequestRepo;
import com.FatimaZahraeELHAJOUI.employee_managment.services.EmployeeServiceImp;
import com.FatimaZahraeELHAJOUI.employee_managment.shared.CustomResponseException;
import com.FatimaZahraeELHAJOUI.employee_managment.shared.GlobalResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author marwa
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    //inject the service implementation and work with in our methods
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private LeaveRequestService leaveRequestService;
    
    @GetMapping
    public ResponseEntity<GlobalResponse<PaginatedResponse<Employee>>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size,
            HttpServletRequest request){
        Page<Employee> employeeAll= employeeService.findAll(page-1,size);
        
        String baseUrl = request.getRequestURL().toString();
        String nextUrl = employeeAll.hasNext()?String.format("%s?page=%d&size=%d", baseUrl, page+1, size):null;
        String prevUrl = employeeAll.hasPrevious()?String.format("%s?page=%d&size=%d", baseUrl, page-1, size):null;
        
        PaginatedResponse<Employee> paginatedResponse= new PaginatedResponse<Employee>(
                employeeAll.getContent(),
                employeeAll.getNumber(),
                employeeAll.getTotalPages(),
                employeeAll.getTotalElements(),
                employeeAll.hasNext(),
                employeeAll.hasPrevious(),
                nextUrl,
                prevUrl
        );
        return new ResponseEntity<>(new GlobalResponse<>(paginatedResponse), HttpStatus.OK);
    }
    
    @GetMapping("/{employeedId}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeedId){
        Employee employee= employeeService.findOne(employeedId);
        return new ResponseEntity<>( new GlobalResponse<>(employee), HttpStatus.OK);
    }  
    
    @PostMapping
    public ResponseEntity<Employee> createOne(@RequestBody @Valid EmployeeCreate employee){
        Employee newemployee= employeeService.createOne(employee);
        return new ResponseEntity<>(newemployee, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{employeedId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeedId) throws CustomResponseException{
        employeeService.deleteOne(employeedId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }
    
    @PutMapping("/{employeedId}")
    public ResponseEntity<Employee> updateOne(@PathVariable UUID employeedId, @RequestBody @Valid EmployeeUpdate employee) throws CustomResponseException{
        Employee updatedEmployee = employeeService.updateOne(employeedId, employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
    
    
    //employee can add a new leave request
    @PostMapping("/{employeedId}/leave-request")
    public ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody @Valid LeaveRequestCreate leaveRequest, @PathVariable UUID employeedId){
        LeaveRequest newRequest= leaveRequestService.createOne(leaveRequest, employeedId);
        return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
    }
    
    //find request by employee id
    @GetMapping("/{employeedId}/leave-request-by-employee")
    public ResponseEntity<GlobalResponse<List<LeaveRequest>>> findeaveRequest(@PathVariable UUID employeedId){
        List<LeaveRequest> EmployeeRequests = leaveRequestService.findAllByEmployeeId(employeedId);
        return new ResponseEntity<>( new GlobalResponse<>(EmployeeRequests), HttpStatus.OK);
    }  
    
}
