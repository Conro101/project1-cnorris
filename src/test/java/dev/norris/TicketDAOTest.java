package dev.norris;

import dev.norris.entities.Ticket;
import dev.norris.repositories.TicketDAO;
import dev.norris.repositories.TicketDAOPostgres;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TicketDAOTest {

    static TicketDAO ticketDAO = new TicketDAOPostgres();
    @Test
    @Order(1)
    void create_ticket_test(){
        Ticket newTicket = new Ticket(0, 100.00, "cnorris", "Pending", "For my dog");
        Ticket savedTicket = ticketDAO.createTicket(newTicket);
        Assertions.assertNotEquals(0, savedTicket.getId());
    }

    @Test
    @Order(2)
    void get_ticket_by_id_test(){
        Ticket returnedTicket = ticketDAO.getTicketById(1);
        Assertions.assertEquals("For my dog", returnedTicket.getDescription());
    }

    @Test
    @Order(3)
    void get_all_tickets_test(){
        List<Ticket> returnedTickets = ticketDAO.getAllTickets();
        Assertions.assertFalse(returnedTickets.isEmpty());
    }

    @Test
    @Order(4)
    void get_all_tickets_by_filter_test(){
        List<Ticket> returnedTickets = ticketDAO.getAllTicketsByFilter("Pending");
        Assertions.assertFalse(returnedTickets.isEmpty());
    }

    @Test
    @Order(5)
    void get_all_tickets_by_username_test(){
        List<Ticket> returnedTickets = ticketDAO.getAllTicketsByUsername("cnorris");
        Assertions.assertFalse(returnedTickets.isEmpty());
    }

    @Test
    @Order(6)
    void get_all_tickets_by_filter_and_username_test(){
        List<Ticket> returnedTickets = ticketDAO.getAllTicketsByFilterAndUsername("Pending", "cnorris");
        Assertions.assertFalse(returnedTickets.isEmpty());
    }

    @Test
    @Order(7)
    void update_ticket_test(){
        Ticket returnedTicket = ticketDAO.getTicketById(1);
        Ticket secondTicket = new Ticket(returnedTicket.getId(),returnedTicket.getAmount(),returnedTicket.getUsername(),"Denied",returnedTicket.getDescription());
        ticketDAO.updateTicket(secondTicket);
        Ticket updatedTicket = ticketDAO.getTicketById(secondTicket.getId());
        Assertions.assertEquals("Denied",updatedTicket.getStatus());
    }

    @Test
    @Order(8)
    void delete_ticket_test(){
        boolean result = ticketDAO.deleteTicketById(1);
        Assertions.assertTrue(result);
    }
}
