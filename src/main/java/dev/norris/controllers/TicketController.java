package dev.norris.controllers;

import com.google.gson.Gson;
import dev.norris.driver.Driver;
import dev.norris.entities.Ticket;
import io.javalin.http.Handler;
import org.eclipse.jetty.util.DateCache;

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
        ctx.status(200);
        ctx.result(json);
    };
    public Handler getAllTickets = (ctx) ->{
        List<Ticket> tickets = Driver.ticketService.getAllTickets();
        Gson gson = new Gson();
        String json = gson.toJson(tickets);
        ctx.status(200);
        ctx.result(json);
    };
    public Handler getAllTicketsByFilter = (ctx) ->{
        String filter = ctx.pathParam("filter");
        List<Ticket> tickets = Driver.ticketService.getAllTicketsByFilter(filter);
        Gson gson = new Gson();
        String json = gson.toJson(tickets);
        ctx.status(200);
        ctx.result(json);
    };
    public Handler getAllTicketsById = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Ticket> tickets = Driver.ticketService.getAllTicketsById(id);
        Gson gson = new Gson();
        String json = gson.toJson(tickets);
        ctx.status(200);
        ctx.result(json);
    };
    public Handler getAllTicketsByFilterAndId = ctx ->{
        String filter = ctx.pathParam("filter");
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Ticket> tickets = Driver.ticketService.getAllTicketsByFilterAndId(filter,id);
        Gson gson = new Gson();
        String json = gson.toJson(tickets);
        ctx.status(200);
        ctx.result(json);
    };
    public Handler updateTicket = (ctx) ->{
        String ticketJson = ctx.body();
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(ticketJson, Ticket.class);
        Ticket updateTicket = Driver.ticketService.updateTicket(ticket);
        String json = gson.toJson(updateTicket);
        ctx.status(202);
        ctx.result(json);
    };
    public Handler deleteTicketById = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = Driver.ticketService.deleteTicketById(id);
        if(result){
            ctx.status(204);
        }
        else{
            ctx.status(400);
            ctx.result("Delete request could not be processed");
        }
    };
}
