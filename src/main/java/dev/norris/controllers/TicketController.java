package dev.norris.controllers;

import com.google.gson.Gson;
import dev.norris.driver.Driver;
import dev.norris.entities.Ticket;
import io.javalin.http.Handler;

import java.util.List;

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
        Gson gson = new Gson();
        String json = gson.toJson(ticket);
        ctx.result(json);
    };
    public Handler getAllTickets = (ctx) ->{
        List<Ticket> tickets = Driver.ticketService.getAllTickets();
        Gson gson = new Gson();
        String json = gson.toJson(tickets);
        ctx.result(json);
    };
    public Handler getAllTicketsByFilter = (ctx) ->{
        String filter = ctx.pathParam("filter");
        List<Ticket> tickets = Driver.ticketService.getAllTicketsByFilter(filter);
        Gson gson = new Gson();
        String json = gson.toJson(tickets);
        ctx.result(json);
    };
    public Handler getAllTicketsById = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Ticket> tickets = Driver.ticketService.getAllTicketsById(id);
        Gson gson = new Gson();
        String json = gson.toJson(tickets);
        ctx.result(json);
    };
    public Handler getAllTicketsByFilterAndId = ctx ->{

    };
    public Handler updateTicket = (ctx) ->{

    };
    public Handler deleteTicketById = (ctx) ->{

    };
}
