package dev.norris.driver;

import dev.norris.controllers.EmployeeController;
import dev.norris.controllers.TicketController;
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
        Javalin app = Javalin.create();

        EmployeeController employeeController = new EmployeeController();
        TicketController ticketController = new TicketController();

        app.post("/employees", employeeController.createEmployee);
        app.post("/tickets", ticketController.createTicket);

        app.get("/employees", employeeController.getEmployeeById);
        app.get("/tickets", ticketController.getAllTickets);
        app.get("/tickets/{id}", ticketController.getTicketById);
        app.get("/tickets/{filter}", ticketController.getAllTicketsByFilter);
        app.get("/employees/tickets/{id}", ticketController.getAllTicketsById);
        app.get("/employees/tickets/{id}/{filter}", ticketController.getAllTicketsByFilterAndId);

        app.put("/employees", employeeController.updateEmployee);
        app.put("/tickets", ticketController.updateTicket);

        app.delete("/employees", employeeController.deleteEmployeeById);
        app.delete("/tickets", ticketController.deleteTicketById);

        app.start();

    }
}
