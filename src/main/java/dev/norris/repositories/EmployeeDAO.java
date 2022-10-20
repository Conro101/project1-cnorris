package dev.norris.repositories;

import dev.norris.entities.Employee;

public interface EmployeeDAO {
    //CRUD- Create, Read, Update, Delete
    Employee createEmployee(Employee employee);
    Employee loginEmployee(Employee employee);
    Employee getEmployeeById(int id);
    Employee updateEmployee(Employee employee);
    boolean deleteEmployeeById(int id);
}
