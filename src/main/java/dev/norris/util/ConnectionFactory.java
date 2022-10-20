package dev.norris.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println("CLASS WASN'T FOUND");
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=practice";

        String username = "postgres";
        String password = "password";

        return DriverManager.getConnection(url, username, password);

    }

}

