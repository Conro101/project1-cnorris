package dev.norris.controllers;

import com.google.gson.Gson;
import dev.norris.driver.Driver;
import dev.norris.entities.Ticket;
import io.javalin.http.Handler;

import java.util.List;

public class TicketController {
    //CRUD- Create, Read, Update, Delete
    public Handler createTicket = (ctx) -> {
        String ticketJson = ctx.body();
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(ticketJson, Ticket.class);
        Ticket registeredTicket = Driver.ticketService.createTicket(ticket);
        switch (registeredTicket.getId()){
            case -1:
                ctx.status(400);
                ctx.result("Request has an invalid amount.");
                break;
            case -2:
                ctx.status(400);
                ctx.result("Request has no user attached.");
                break;
            case -3:
                ctx.status(400);
                ctx.result("Request has an invalid status.");
                break;
            case -4:
                ctx.status(400);
                ctx.result("Request must include a description.");
                break;
            default:
                String json = gson.toJson(registeredTicket);
                ctx.status(201);
                ctx.result(json);
                break;
        }
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
        System.out.println("Ready!");
        System.out.println(ctx.pathParam("filter"));
        String filter = ctx.pathParam("filter");
        System.out.println(filter);
        List<Ticket> tickets = Driver.ticketService.getAllTicketsByFilter(filter);
        Gson gson = new Gson();
        String json = gson.toJson(tickets);
        ctx.status(200);
        ctx.result(json);
    };
    public Handler getAllTicketsByUsername = (ctx) ->{
        String username = ctx.pathParam("username");
        List<Ticket> tickets = Driver.ticketService.getAllTicketsByUsername(username);
        Gson gson = new Gson();
        String json = gson.toJson(tickets);
        ctx.status(200);
        ctx.result(json);
    };
    public Handler getAllTicketsByFilterAndUsername = ctx ->{
        String filter = ctx.pathParam("filter");
        String username = ctx.pathParam("username");
        List<Ticket> tickets = Driver.ticketService.getAllTicketsByFilterAndUsername(filter,username);
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
