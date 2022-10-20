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
    };
    public Handler getTicketById = (ctx) ->{

    };
    public Handler getAllTickets = (ctx) ->{

    };
    public Handler getAllTicketsByFilter = (ctx) ->{

    };
    public Handler getAllTicketsByUsername = (ctx) ->{

    };
    public Handler updateTicket = (ctx) ->{

    };
    public Handler deleteTicketById = (ctx) ->{

    };
}
