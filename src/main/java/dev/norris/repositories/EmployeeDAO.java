package dev.norris.repositories;

import dev.norris.entities.Employee;

public interface EmployeeDAO {
    //CRUD- Create, Read, Update, Delete
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(int id);
    Employee updateEmployee(Employee employee);
    boolean deleteEmployeeByUsername(String username);
}
