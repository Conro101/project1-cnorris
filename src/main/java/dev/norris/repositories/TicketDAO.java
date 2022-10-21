package dev.norris.repositories;

import dev.norris.entities.Ticket;

import java.util.List;

public interface TicketDAO {
    //CRUD- Create, Read, Update, Delete
    Ticket createTicket(Ticket ticket);
    Ticket getTicketById(int id);
    List<Ticket> getAllTickets();
    List<Ticket> getAllTicketsByFilter(String filter);
    List<Ticket> getAllTicketsByUsername(String username);
    List<Ticket> getAllTicketsByFilterAndUsername(String filter, String username);
    Ticket updateTicket(Ticket ticket);
    boolean deleteTicketById(int id);
}
