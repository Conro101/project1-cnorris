package dev.norris.repositories;

import dev.norris.entities.Ticket;
import dev.norris.util.ConnectionFactory;

import java.sql.*;
import java.util.List;

public class TicketDAOPostgres implements TicketDAO{
    @Override
    public Ticket createTicket(Ticket ticket) {
        try(Connection connection = ConnectionFactory.getConnection()){
            //INSERT INTO ticket VALUES (DEFAULT, 10.00, "jsmith", "Pending", "Bought Lunch");
            String sql = "insert into tickets values(default, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getUsername());
            preparedStatement.setString(3, ticket.getStatus());
            preparedStatement.setString(4, ticket.getDescription());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int generatedKey = resultSet.getInt("id");
            ticket.setId(generatedKey);
            return ticket;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
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
    public List<Ticket> getAllTicketsById(int id) {
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
