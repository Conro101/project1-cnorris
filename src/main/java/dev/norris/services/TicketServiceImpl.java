package dev.norris.services;

import dev.norris.entities.Ticket;
import dev.norris.repositories.TicketDAO;

import java.util.List;

public class TicketServiceImpl implements TicketService{
    private TicketDAO ticketDAO;

    public TicketServiceImpl(TicketDAO ticketDAO){
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return null;
    }

    @Override
    public Ticket getTicketById(int id) {
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return null;
    }

    @Override
    public List<Ticket> getAllTicketsByFilter(String filter) {
        return null;
    }

    @Override
    public List<Ticket> getAllTicketsByUsername(String username) {
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return null;
    }

    @Override
    public boolean deleteTicketById(int id) {
        return false;
    }
}
