package com.sdu.edu.kz.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBProvider {
    private static String USER = "postgres";
    private static String PASSWORD = "0000";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sdp_final", USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
