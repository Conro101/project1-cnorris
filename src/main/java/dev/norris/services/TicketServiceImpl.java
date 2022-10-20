package dev.norris.services;

import dev.norris.entities.Ticket;
import dev.norris.repositories.TicketDAO;

import java.util.List;

public class TicketServiceImpl implements TicketService{
    private TicketDAO ticketDAO;

    //These are placeholders. If/when user verification is implemented, these will be replaced
    private boolean userLoggedIn = true;
    private boolean userManager = true;

    public TicketServiceImpl(TicketDAO ticketDAO){
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        if(ticket.getAmount() <= 0.00){
            throw new RuntimeException("Ticket must have a positive value!");
        }
        if(ticket.getUsername().length() == 0){
            throw new RuntimeException("Ticket must be attached to a user!");
        }
        if(userLoggedIn != true){
            throw new RuntimeException("User is not logged in!");
        }
        if(userManager == true){
            throw new RuntimeException("Managers cannot log tickets!");
        }
        Ticket savedTicket = this.ticketDAO.createTicket(ticket);
        return savedTicket;
    }

    @Override
    public Ticket getTicketById(int id) {
        if(userLoggedIn != true){
            throw new RuntimeException("Only users can get tickets!");
        }
        return this.ticketDAO.getTicketById(id);
    }

    @Override
    public List<Ticket> getAllTickets() {
        if (userLoggedIn != true){
            throw new RuntimeException("Only users can get tickets!");
        }
        if(userManager != true){
            throw new RuntimeException("Only managers can get all tickets!");
        }
        return this.ticketDAO.getAllTickets();
    }

    @Override
    public List<Ticket> getAllTicketsByFilter(String filter) {
        if (userLoggedIn != true){
            throw new RuntimeException("Only users can get tickets!");
        }
        if(userManager != true){
            throw new RuntimeException("Only managers can get all tickets!");
        }
        return this.ticketDAO.getAllTicketsByFilter(filter);
    }

    @Override
    public List<Ticket> getAllTicketsById(int id) {
        if (userLoggedIn != true){
            throw new RuntimeException("Only users can get tickets!");
        }
        return this.ticketDAO.getAllTicketsById(id);
    }

    @Override
    public List<Ticket> getAllTicketsByFilterAndId(String filter, int id){
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
