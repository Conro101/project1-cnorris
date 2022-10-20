package dev.norris.repositories;

import dev.norris.entities.Employee;
import dev.norris.util.ConnectionFactory;

import java.sql.*;

public class EmployeeDAOPostgres implements EmployeeDAO{

    @Override
    public Employee createEmployee(Employee employee) {
        try(Connection connection = ConnectionFactory.getConnection()){
            //INSERT INTO employee VALUES (DEFAULT, "jsmith","password",false);
            String sql = "insert into employees values(default, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getPassword());
            preparedStatement.setBoolean(3, employee.isManager());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int generatedKey = resultSet.getInt("id");
            employee.setId(generatedKey);
            return employee;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        return false;
    }
}
