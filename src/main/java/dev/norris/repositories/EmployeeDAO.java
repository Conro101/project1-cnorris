package dev.norris.repositories;

import dev.norris.entities.Employee;

public interface EmployeeDAO {
    Employee createEmployee(Employee employInput);

    Employee getEmployeeByID(Employee employInput);
}
