package dev.norris.entities;

public class Ticket{
    private int id;
    private double amount;
    private String username;
    private String status;
    private String description;

    public Ticket() {
    }

    public Ticket(int id, double amount, String username, String status, String description) {
        this.id = id;
        this.amount = amount;
        this.username = username;
        this.status = status;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
