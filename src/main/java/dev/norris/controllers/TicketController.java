package dev.norris.controllers;

import com.google.gson.Gson;
import dev.norris.driver.Driver;
import dev.norris.entities.Ticket;
import io.javalin.http.Handler;

public class TicketController {
    //CRUD- Create, Read, Update, Delete
    public Handler createTicket = (ctx) -> {
        String json = ctx.body();
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(json, Ticket.class);
        Ticket registeredTicket = Driver.ticketService.createTicket(ticket);
        String ticketJson = gson.toJson(registeredTicket);
        ctx.status(201);
        ctx.result(ticketJson);
    };
    public Handler getTicketById = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        Ticket ticket = Driver.ticketService.getTicketById(id);
    };
    public Handler getAllTickets = (ctx) ->{

    };
    public Handler getAllTicketsByFilter = (ctx) ->{

    };
    public Handler getAllTicketsById = (ctx) ->{

    };
    public Handler updateTicket = (ctx) ->{

    };
    public Handler deleteTicketById = (ctx) ->{

    };
}
