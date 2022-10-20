package dev.norris.services;

import dev.norris.driver.Driver;
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
        if(ticket.getAmount() <= 0.00){
            throw new RuntimeException("Ticket must have a positive value!");
        }
        if(ticket.getUsername().length() == 0){
            throw new RuntimeException("Ticket must be attached to a user!");
        }
        if(Driver.currentEmployee == null){
            throw new RuntimeException("User is not logged in!");
        }
        if(Driver.currentEmployee.isManager()){
            throw new RuntimeException("Managers cannot log tickets!");
        }
        return this.ticketDAO.createTicket(ticket);
    }

    @Override
    public Ticket getTicketById(int id) {
        if(Driver.currentEmployee == null){
            throw new RuntimeException("User is not logged in!");
        }
        return this.ticketDAO.getTicketById(id);
    }

    @Override
    public List<Ticket> getAllTickets() {
        if (Driver.currentEmployee == null){
            throw new RuntimeException("User is not logged in!");
        }
        if(!Driver.currentEmployee.isManager()){
            throw new RuntimeException("Only managers can get all tickets!");
        }
        return this.ticketDAO.getAllTickets();
    }

    @Override
    public List<Ticket> getAllTicketsByFilter(String filter) {
        if (Driver.currentEmployee == null){
            throw new RuntimeException("Only users can get tickets!");
        }
        if(!Driver.currentEmployee.isManager()){
            throw new RuntimeException("Only managers can get all tickets!");
        }
        return this.ticketDAO.getAllTicketsByFilter(filter);
    }

    @Override
    public List<Ticket> getAllTicketsById(int id) {
        if (Driver.currentEmployee == null){
            throw new RuntimeException("User is not logged in!");
        }
        return this.ticketDAO.getAllTicketsById(id);
    }

    @Override
    public List<Ticket> getAllTicketsByFilterAndId(String filter, int id){
        if (Driver.currentEmployee == null){
            throw new RuntimeException("User is not logged in!");
        }
        return this.ticketDAO.getAllTicketsByFilterAndId(filter,id);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        if (Driver.currentEmployee == null){
            throw new RuntimeException("User is not logged in!");
        }
        if(!Driver.currentEmployee.isManager()){
            throw new RuntimeException("Only managers can update tickets!");
        }
        return this.ticketDAO.updateTicket(ticket);
    }

    @Override
    public boolean deleteTicketById(int id) {
        if (Driver.currentEmployee == null){
            throw new RuntimeException("User is not logged in!");
        }
        if(!Driver.currentEmployee.isManager()){
            throw new RuntimeException("Only managers can update tickets!");
        }
        return this.ticketDAO.deleteTicketById(id);
    }
}
