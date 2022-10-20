package dev.norris.entities;

import java.util.Objects;

public class Employee {
    private int id;
    private String username;
    private String password;
    boolean manager = false;

    public Employee() {
    }

    public Employee(int id, String username, String password, boolean manager) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int userID) {
        this.id = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (manager != employee.manager) return false;
        if (!Objects.equals(username, employee.username)) return false;
        return Objects.equals(password, employee.password);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (manager ? 1 : 0);
        return result;
    }
}
