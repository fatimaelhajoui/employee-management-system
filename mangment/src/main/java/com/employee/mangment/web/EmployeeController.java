/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.employee.mangment.web;


import com.employee.mangment.enteties.Employee;
import com.employee.mangment.repository.EmployeeRepository;
import fr.insee.lunatic.utils.Modele;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author marwa
 */
@Controller 
@AllArgsConstructor
public class EmployeeController {
    
    private EmployeeRepository employeeRepository ;
    
    @GetMapping("/")
     public String page()
    {
        return "redirect:/user/index";
    }
    
    @GetMapping("/user/index") 
    public String index(Model model,
            @RequestParam(name="page", defaultValue = "0") int p,
            @RequestParam(name="size", defaultValue = "6") int s,
            @RequestParam(name="keyword", defaultValue = "") String k)
    {
        
        Page <Employee> Employeepage= employeeRepository.findByFullnameContains(k,PageRequest.of(p,s));
        model.addAttribute("listEmployee", Employeepage.getContent());
        model.addAttribute("pages",new int[Employeepage.getTotalPages()]);
        model.addAttribute("current",p);
        model.addAttribute("Mykeyword",k);
        return "employees";
        
    }
    
    @GetMapping("/admin/delete")
    public String delete(Long id, String keyword, int page){
        employeeRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    
    @GetMapping("/admin/add_form")
    public String add_form(Model model){
        model.addAttribute("employee",new Employee());
        return "formEmployee";
    }
    @PostMapping(path = "/admin/save")
    public String save(Model model,@Valid Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formEmployee"; 
        employeeRepository.save(employee);
        return "redirect:/user/index";
    }
    
    @GetMapping("/admin/edit")
    public String edit_form(Model model, Long id, 
            @RequestParam(name="keyword", defaultValue = "") String keyword, 
            @RequestParam(name="page", defaultValue = "0") int page){
        Employee employee= employeeRepository.findById(id).orElse(null);
        if(employee==null) throw new RuntimeException("Employee Not exist ");
        model.addAttribute("employee",employee);
        model.addAttribute("current",page);
        model.addAttribute("keyword",keyword);
        return "formEmployeeEdit";
    }
    
    @PostMapping(path = "/admin/update")
    public String update(Model model,@Valid Employee employee, BindingResult bindingResult,
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name="keyword", defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) {
            model.addAttribute("current",page);
            model.addAttribute("keyword",keyword);
            return "formEmployeeEdit"; 
        }
        employeeRepository.save(employee);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
}
