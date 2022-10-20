package dev.norris.services;

import dev.norris.entities.Ticket;

import java.util.List;

public interface TicketService {
    //CRUD- Create, Read, Update, Delete
    Ticket createTicket(Ticket ticket);
    Ticket getTicketById(int id);
    List<Ticket> getAllTickets();
    List<Ticket> getAllTicketsByFilter(String filter);
    List<Ticket> getAllTicketsById(int id);
    List<Ticket> getAllTicketsByFilterAndId(String filter, int id);
    Ticket updateTicket(Ticket ticket);
    boolean deleteTicketById(int id);
}
