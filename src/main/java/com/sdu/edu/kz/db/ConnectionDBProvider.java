package com.sdu.edu.kz.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDBProvider {
    private static String DB_URL = "jdbc:postgresql://localhost:5432/sdp_final";
    private static String USER = "postgres";
    private static String PASSWORD = "0000";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
