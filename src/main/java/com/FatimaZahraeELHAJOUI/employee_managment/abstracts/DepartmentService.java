/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.abstracts;

import com.FatimaZahraeELHAJOUI.employee_managment.dtos.DepartmentCreate;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.Department;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author marwa
 */
public interface DepartmentService {
    Department findOne(UUID departmentID);
    List<Department> findAll();
    Department createOne(DepartmentCreate departmentCreate);
    Department updateOne(UUID departmentID);
    void deleteOne(UUID departmentID);
}
