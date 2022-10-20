package dev.norris.repositories;

import dev.norris.driver.Driver;
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
    public boolean loginEmployee(Employee employee){
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from employees where username = ?, password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                resultSet.next();

                Employee loggedEmployee = new Employee();
                loggedEmployee.setId(resultSet.getInt("id"));
                loggedEmployee.setUsername(resultSet.getString("username"));
                loggedEmployee.setPassword(resultSet.getString("password"));
                loggedEmployee.setManager(resultSet.getBoolean("manager"));
                Driver.currentEmployee = loggedEmployee;
                return true;
            }else {
                return false;
            }

        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from employees where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setUsername(resultSet.getString("username"));
            employee.setPassword(resultSet.getString("password"));
            employee.setManager(resultSet.getBoolean("manager"));

            return employee;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "update employees set username = ?, password = ?, manager = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, employee.getUsername());
            preparedStatement.setString(2, employee.getPassword());
            preparedStatement.setBoolean(3,employee.isManager());
            preparedStatement.setInt(4,employee.getId());

            preparedStatement.executeUpdate();
            return employee;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "delete from employees where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,id);

            preparedStatement.execute();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
