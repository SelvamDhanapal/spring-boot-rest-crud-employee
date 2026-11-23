package com.example.demo.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping ("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private JsonMapper jsonMapper;

    public EmployeeRestController(EmployeeService employeeService,
        JsonMapper jsonMapper
    ) {
        this.employeeService = employeeService;
        this.jsonMapper = jsonMapper;
    }
    
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if(employee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
 
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        
        return employeeService.save(employee);
    }
    

    @PatchMapping ("/employees/{employeeId}")
    public Employee patchEmployee(@PathVariable int employeeId, 
        @RequestBody Map<String, Object> patchPayload) {
        Employee existingEmployee = employeeService.findById(employeeId);
        if(existingEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId); 
        }
 
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body - " + employeeId); 
        }

        Employee patchEmployee = jsonMapper.updateValue(existingEmployee, patchPayload);
        return employeeService.save(patchEmployee);

    }

    @DeleteMapping ("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if(employee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId); 
        }
        
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;   
    }    
    
}
