/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.services;

import com.FatimaZahraeELHAJOUI.employee_managment.abstracts.EmailService;
import com.FatimaZahraeELHAJOUI.employee_managment.abstracts.EmployeeService;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.EmployeeCreate;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.EmployeeUpdate;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.Department;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.Employee;
import com.FatimaZahraeELHAJOUI.employee_managment.repositories.DepartmentRepo;
import com.FatimaZahraeELHAJOUI.employee_managment.repositories.EmployeeRepo;
import com.FatimaZahraeELHAJOUI.employee_managment.shared.CustomResponseException;
import com.FatimaZahraeELHAJOUI.employee_managment.utils.SecurityUtils;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author marwa
 */
@Service
public class EmployeeServiceImp implements EmployeeService{

    
    @Autowired
    private EmployeeRepo employeeRepo;
    
    @Autowired
    private DepartmentRepo departmentRepo;
    
    @Autowired
    private SecurityUtils securityUtils;
    
    @Autowired
    private EmailServiceImp emailService;
    
    @Override
    @PreAuthorize("@securityUtils.isOwner(#employeedId)")
    public Employee findOne(UUID employeedId) {
        
        Employee employee = null;
        try {
            employee = employeeRepo.findById(employeedId)
                    .orElseThrow(
                            ()->CustomResponseException.ResourceNotFound( 
                                    "Employee with ID: "+ employeedId +" not found"
                            ));
        } catch (CustomResponseException ex) {
            Logger.getLogger(EmployeeServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        return employee;
    }

    @Override
        public Page<Employee> findAll(int page, int size) {
            Pageable pageable = PageRequest.of(page, size);
            return employeeRepo.findAll(pageable);
    }
    
    @Override
    public void deleteOne(UUID employeedId){
        Optional<Employee> employee= employeeRepo.findById(employeedId);
        employee.ifPresent(value -> employeeRepo.deleteById(value.getId()));
    }
    
    @Override
    public Employee updateOne(UUID employeedId,EmployeeUpdate employee){
        Optional<Employee> existEmployee= employeeRepo.findById(employeedId);
        if(existEmployee.isEmpty()){
            try {
                throw CustomResponseException.ResourceNotFound("Employee with id ="+ employeedId +" not found");
            } catch (CustomResponseException ex) {
                Logger.getLogger(EmployeeServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            Employee updatedEmployee = existEmployee.get();
            updatedEmployee.setFirstName(employee.getFirstName());
            updatedEmployee.setLastName(employee.getLastName());
            updatedEmployee.setPhoneNumber(employee.getPhoneNumber());
            updatedEmployee.setPosition(employee.getPosition());
            updatedEmployee.setHireDate(employee.getHireDate());
            
            employeeRepo.save(updatedEmployee);
            return updatedEmployee;
        
    }

    @Transactional
    @Override
    public Employee createOne(EmployeeCreate employeeCreate) {
        Employee employee= new Employee();
      
         Department department = null;
        try {
            department = departmentRepo.findById(employeeCreate.getDepartmenId())
                    .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                            "Department with id " + employeeCreate.getDepartmenId() + " not found"
                    ));
        } catch (CustomResponseException ex) {
            Logger.getLogger(EmployeeServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String token = UUID.randomUUID().toString();
        employee.setVerified(false);
        employee.setAccountCreationToken(token);
           
              employee.setFirstName(employeeCreate.getFirstName());
              employee.setLastName(employeeCreate.getLastName());
              employee.setEmail(employeeCreate.getEmail());
              employee.setPhoneNumber(employeeCreate.getPhoneNumber());
              employee.setPosition(employeeCreate.getPosition());
              employee.setHireDate(employeeCreate.getHireDate());
              employee.setDepartment(department);
              
              employeeRepo.save(employee);
              
              emailService.sendAccountCreationEmail(employee.getEmail(), token);
                
              return employee;
    }

   
}
