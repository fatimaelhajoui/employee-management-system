/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.controller;

import com.FatimaZahraeELHAJOUI.employee_managment.abstracts.*;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.DepartmentCreate;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.Department;
import com.FatimaZahraeELHAJOUI.employee_managment.shared.CustomResponseException;
import com.FatimaZahraeELHAJOUI.employee_managment.shared.GlobalResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marwa
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    //inject the service implementation and work with in our methods
    @Autowired
    private DepartmentService departmentService;
    
    @GetMapping
    public ResponseEntity<GlobalResponse<List<Department>>> findAll(){
        List<Department> departmentAll= departmentService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(departmentAll), HttpStatus.OK);
    }
    
    @GetMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> findOne(@PathVariable UUID departmentId){
        Department department= departmentService.findOne(departmentId);
        return new ResponseEntity<>( new GlobalResponse<>(department), HttpStatus.OK);
    }  
    
    @PostMapping
    public ResponseEntity<Department> createOne(@RequestBody @Valid DepartmentCreate departmentCreate){
        Department newDepartment= departmentService.createOne(departmentCreate);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID departmentId) throws CustomResponseException{
        departmentService.deleteOne(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }
    
}
