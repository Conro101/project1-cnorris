package dev.norris.services;

import dev.norris.entities.Employee;
import dev.norris.repositories.EmployeeDAO;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if(employee.getUsername().length() == 0){
            throw new RuntimeException("Employee username must not be blank!");
        }
        if(employee.getPassword().length() == 0){
            throw new RuntimeException("Employee password must not be blank!");
        }
        Employee savedEmployee = this.employeeDAO.createEmployee(employee);
        return savedEmployee;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return this.employeeDAO.getEmployeeById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if(employee.getUsername().length() == 0){
            throw new RuntimeException("Employee username must not be blank!");
        }
        if(employee.getPassword().length() == 0){
            throw new RuntimeException("Employee password must not be blank!");
        }
        Employee savedEmployee = this.employeeDAO.updateEmployee(employee);
        return savedEmployee;
    }

    @Override
    public boolean deleteEmployeeByUsername(String username) {
        return this.employeeDAO.deleteEmployeeByUsername(username);
    }
}