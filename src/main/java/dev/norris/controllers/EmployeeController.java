package dev.norris.controllers;

import com.google.gson.Gson;
import dev.norris.driver.Driver;
import dev.norris.entities.Employee;
import io.javalin.http.Handler;

public class EmployeeController {
   //CRUD- Create, Read, Update, Delete
    public Handler createEmployee = (ctx) ->{
        String json = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);
    };

    public Handler getEmployeeById = (ctx) ->{

    };

    public Handler updateEmployee = (ctx) ->{

    };

    public Handler deleteEmployeeById = (ctx) ->{

    };
}
