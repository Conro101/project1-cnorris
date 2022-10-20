package dev.norris.driver;

import dev.norris.repositories.EmployeeDAOPostgres;
import dev.norris.repositories.TicketDAOPostgres;
import dev.norris.services.EmployeeService;
import dev.norris.services.EmployeeServiceImpl;
import dev.norris.services.TicketService;
import dev.norris.services.TicketServiceImpl;
import io.javalin.Javalin;


public class Driver {
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
    public static TicketService ticketService = new TicketServiceImpl(new TicketDAOPostgres());
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/).get("/", ctx -> ctx.result("Hello World")).start(7070);

    }
}
