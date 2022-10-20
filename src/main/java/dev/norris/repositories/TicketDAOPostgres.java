package dev.norris.repositories;

import dev.norris.entities.Ticket;
import dev.norris.util.ConnectionFactory;
import org.eclipse.jetty.util.DateCache;

import java.sql.*;
import java.util.ArrayList;
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
            return null;
        }
    }

    @Override
    public Ticket getTicketById(int id) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from tickets where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setAmount(rs.getDouble("amount"));
            ticket.setUsername(rs.getString("username"));
            ticket.setStatus(rs.getString("status"));
            ticket.setDescription(rs.getString("description"));

            return ticket;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ticket> getAllTickets() {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from tickets";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Ticket> ticketList = new ArrayList<>();
            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setAmount(rs.getDouble("amount"));
                ticket.setUsername(rs.getString("username"));
                ticket.setStatus(rs.getString("status"));
                ticket.setDescription(rs.getString("description"));
                ticketList.add(ticket);
            }
            return ticketList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ticket> getAllTicketsByFilter(String filter) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from tickets where filter = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, filter);

            ResultSet rs = preparedStatement.executeQuery();

            List<Ticket> ticketList = new ArrayList<>();
            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setAmount(rs.getDouble("amount"));
                ticket.setUsername(rs.getString("username"));
                ticket.setStatus(rs.getString("status"));
                ticket.setDescription(rs.getString("description"));
                ticketList.add(ticket);
            }
            return ticketList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ticket> getAllTicketsById(int id) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from tickets where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            List<Ticket> ticketList = new ArrayList<>();
            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setAmount(rs.getDouble("amount"));
                ticket.setUsername(rs.getString("username"));
                ticket.setStatus(rs.getString("status"));
                ticket.setDescription(rs.getString("description"));
                ticketList.add(ticket);
            }
            return ticketList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ticket> getAllTicketsByFilterAndId(String filter, int id){
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "select * from tickets where filter = ? and id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,filter);
            preparedStatement.setInt(2, id);

            ResultSet rs = preparedStatement.executeQuery();

            List<Ticket> ticketList = new ArrayList<>();
            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("id"));
                ticket.setAmount(rs.getDouble("amount"));
                ticket.setUsername(rs.getString("username"));
                ticket.setStatus(rs.getString("status"));
                ticket.setDescription(rs.getString("description"));
                ticketList.add(ticket);
            }
            return ticketList;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "update tickets set amount = ?, username = ?, status = ?, description = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getUsername());
            preparedStatement.setString(3, ticket.getStatus());
            preparedStatement.setString(4, ticket.getDescription());
            preparedStatement.setInt(5, ticket.getId());

            preparedStatement.execute();
            return ticket;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteTicketById(int id) {
        try(Connection connection = ConnectionFactory.getConnection()){
            String sql = "delete from tickets where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            preparedStatement.execute();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
