/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.employee.mangment.repository;

import com.employee.mangment.enteties.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marwa
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByFullnameContains(String keyword, Pageable pageable);
}



