/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.FatimaZahraeELHAJOUI.employee_managment.abstracts;

import com.FatimaZahraeELHAJOUI.employee_managment.dtos.EmployeeCreate;
import com.FatimaZahraeELHAJOUI.employee_managment.dtos.EmployeeUpdate;
import com.FatimaZahraeELHAJOUI.employee_managment.entities.Employee;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author marwa
 */
public interface EmployeeService {
    Employee findOne(UUID employeeId);
    Page<Employee> findAll(int page, int size);
    void deleteOne(UUID employeedId);
    Employee updateOne(UUID employeedId,EmployeeUpdate employee);
    Employee createOne(EmployeeCreate employee);
}
