package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        // Implementation of the method to retrieve all employees
        return employeeDAO.findAll(); // Return the list of all employees
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id); // Return the employee with the given ID or null if not found
    }

    @Transactional 
    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee); // Save the employee and return the saved employee
    }

    @Transactional 
    @Override
    public void deleteById(int id) {
        employeeDAO.deleteById(id); // Delete the employee with the given ID
    }
}



