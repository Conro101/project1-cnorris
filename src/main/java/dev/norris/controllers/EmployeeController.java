package dev.norris.controllers;

import com.google.gson.Gson;
import dev.norris.driver.Driver;
import dev.norris.entities.Employee;
import io.javalin.http.Handler;

public class EmployeeController {
   //CRUD- Create, Read, Update, Delete
    public Handler createEmployee = (ctx) ->{
        String employeeJson = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(employeeJson, Employee.class);
        Employee registeredEmployee = Driver.employeeService.createEmployee(employee);
        if(registeredEmployee.getUsername().length() != 0){
            String json = gson.toJson(registeredEmployee);
            ctx.status(201);
            ctx.result(json);
        }
        else {
            ctx.status(400);
            ctx.result("User already exists!");
        }
    };

    public Handler loginEmployee = (ctx) ->{
        String employeeJson = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(employeeJson, Employee.class);
        boolean result = Driver.employeeService.loginEmployee(employee);
        if(result){
            ctx.status(202);
            ctx.result("Successfully logged in");
        }
        else{
            ctx.status(400);
            ctx.result("Could not process your login request");
        }
    };

    public Handler getEmployeeById = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        Employee employee = Driver.employeeService.getEmployeeById(id);
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        ctx.status(200);
        ctx.result(json);
    };

    public Handler updateEmployee = (ctx) ->{
        String employeeJson = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(employeeJson, Employee.class);
        Employee updateEmployee = Driver.employeeService.updateEmployee(employee);
        String json = gson.toJson(updateEmployee);
        ctx.status(202);
        ctx.result(json);
    };

    public Handler deleteEmployeeById = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = Driver.employeeService.deleteEmployeeById(id);
        if (result){
            ctx.status(204);
        }
        else {
            ctx.status(400);
            ctx.result("Delete request could not be processed");
        }
    };
}
