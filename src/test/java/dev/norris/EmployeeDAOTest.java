package dev.norris;

import dev.norris.entities.Employee;
import dev.norris.repositories.EmployeeDAO;
import dev.norris.repositories.EmployeeDAOPostgres;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTest {

    static EmployeeDAO employeeDAO = new EmployeeDAOPostgres();
    @Test
    @Order(1)
    void create_employee_test(){
        Employee newEmployee = new Employee(0, "cnorris","password",false);
        Employee savedEmployee = employeeDAO.createEmployee(newEmployee);
        Assertions.assertNotEquals(0,savedEmployee.getId());
    }

    @Test
    @Order(2)
    void login_employee_test(){
        Employee newEmployee = new Employee(0, "cnorris","password",false);
        boolean result = employeeDAO.loginEmployee(newEmployee);
        Assertions.assertTrue(result);
    }

    @Test
    @Order(3)
    void get_employee_by_id_test(){
        Employee returnedEmployee = employeeDAO.getEmployeeById(1);
        Assertions.assertEquals("cnorris",returnedEmployee.getUsername());
    }

    @Test
    @Order(4)
    void update_employee_test(){
        Employee returnedEmployee = employeeDAO.getEmployeeById(1);
        Employee secondEmployee = new Employee(returnedEmployee.getId(),"connorn",returnedEmployee.getPassword(), returnedEmployee.isManager());
        employeeDAO.updateEmployee(secondEmployee);
        Employee updatedEmployee = employeeDAO.getEmployeeById(secondEmployee.getId());
        Assertions.assertEquals("connorn",updatedEmployee.getUsername());
    }

    @Test
    @Order(5)
    void delete_employee_test(){
        boolean result = employeeDAO.deleteEmployeeById(1);
        Assertions.assertTrue(result);
    }
}
