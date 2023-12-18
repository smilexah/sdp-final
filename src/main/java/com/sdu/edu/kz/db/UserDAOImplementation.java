package com.sdu.edu.kz.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImplementation implements UserDAO {
    static Connection connection;
    static PreparedStatement statement;

    @Override
    public int insertUser(User user) {
        int userId = -1;
        try {
            String query = "INSERT INTO users VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getPassword());
                statement.executeUpdate();

                // Retrieve the generated user ID
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public User getUser(String userName, String password) {
        User user = new User();
        try {
            // Initialize connection if it's null or closed
            if (connection == null || connection.isClosed()) {
                connection = ConnectionDBProvider.getConnection();
            }

            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE userName = ? AND password = ?")) {
                statement.setString(1, userName);
                statement.setString(2, password);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        user.setId(resultSet.getInt("id"));
                        user.setUserName(resultSet.getString("userName"));
                        user.setPassword(resultSet.getString("password"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
