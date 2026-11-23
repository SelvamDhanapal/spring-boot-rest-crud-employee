package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        // Implementation of the method to retrieve all employees
        return employeeRepository.findAll(); // Return the list of all employees
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findById(id).
        orElseThrow(() -> new RuntimeException("Employee not found with id: " + id)); // Find the employee by ID or throw an exception if not found       
    }

    @Transactional 
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee); // Save the employee and return the saved employee
    }

    @Transactional 
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id); // Delete the employee with the given ID
    }
}



