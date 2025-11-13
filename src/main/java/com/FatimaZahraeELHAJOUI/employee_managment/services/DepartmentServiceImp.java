/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.services;

import com.FatimaZahraeELHAJOUI.employee_managment.abstracts.DepartmentService;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.DepartmentCreate;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.Department;
import com.FatimaZahraeELHAJOUI.employee_managment.repositories.DepartmentRepo;
import com.FatimaZahraeELHAJOUI.employee_managment.shared.CustomResponseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author marwa
 */
@Service
public class DepartmentServiceImp implements DepartmentService{
    
    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public Department findOne(UUID departmentID) {
        Department department = null;
        try {
            department =departmentRepo.findById(departmentID).orElseThrow(
                    ()->CustomResponseException.ResourceNotFound(
                            "Department with ID: "+ departmentID +" not found"
                    ));
        } catch (CustomResponseException ex) {
            Logger.getLogger(DepartmentServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return department;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public Department createOne(DepartmentCreate departmentCreate)  {
        Department department= new Department();
        
        department.setName(departmentCreate.getName());
        departmentRepo.save(department);
        return department;
    }

    @Override
    public Department updateOne(UUID departmentID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteOne(UUID departmentID) {
        Optional<Department> department= departmentRepo.findById(departmentID);
        department.ifPresent(value -> departmentRepo.deleteById(value.getId()));
    }

    
}
