package com.sdu.edu.kz.db;

import java.sql.*;

public class DatabaseManager {
    static Connection connection;
//    private void createTables() {
//        try {
//            String createUserTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
//                    "id SERIAL PRIMARY KEY," +
//                    "name VARCHAR(255) NOT NULL," +
//                    "password VARCHAR(255) NOT NULL)";
//            executeUpdate(createUserTableQuery);
//
//            String createBookingTableQuery = "CREATE TABLE IF NOT EXISTS booking (" +
//                    "id SERIAL PRIMARY KEY," +
//                    "user_id INT," +
//                    "price DOUBLE PRECISION NOT NULL," +
//                    "description VARCHAR(255)," +
//                    "FOREIGN KEY (user_id) REFERENCES users(id))";
//            executeUpdate(createBookingTableQuery);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    public int addUser(String name, String password) {
//        int userId = -1;
//        try {
//            String query = "INSERT INTO users (name, password) VALUES (?, ?)";
//            try (PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
//                statement.setString(1, name);
//                statement.setString(2, password);
//                statement.executeUpdate();
//
//                // Retrieve the generated user ID
//                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
//                    if (generatedKeys.next()) {
//                         userId = generatedKeys.getInt(1);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return userId;
//    }


    public void addBooking(double price, int user_id , String description) {

        try {
            String query = "INSERT INTO booking (price,user_id, description) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDouble(1, price);
                statement.setInt(2, user_id);
                statement.setString(3, description);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String getPasswordByUsername(String userName) {
        try {
            String query = "SELECT password FROM users WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userName);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("password");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeUpdate(String query) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        }
    }
   public void showAllBookings() {
       try {
           String query = "SELECT * FROM booking";
           try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
               while (resultSet.next()) {
                   int bookingId = resultSet.getInt("id");
                   int userId = resultSet.getInt("user_id");
                   double price = resultSet.getDouble("price");
                   String description = resultSet.getString("description");

                   // Assuming you have a toString method in your Booking class
                   System.out.println("Booking ID: " + bookingId);
                   System.out.println("User ID: " + userId);
                   System.out.println("Price: " + price);
                   System.out.println("Description: " + description);
                   System.out.println();
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
    public void showBookingsForUserName(String userName) {
        try {
            String query = "SELECT b.id AS booking_id, b.price, b.description, u.id AS user_id FROM booking b " +
                    "JOIN users u ON b.user_id = u.id WHERE u.name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int bookingId = resultSet.getInt("booking_id");
                        int userId = resultSet.getInt("user_id");
                        double price = resultSet.getDouble("price");
                        String description = resultSet.getString("description");

                        // Assuming you have a toString method in your Booking class
                        System.out.println("Booking ID: " + bookingId);
                        System.out.println("User ID: " + userId);
                        System.out.println("Price: " + price);
                        System.out.println("Description: " + description);
                        System.out.println();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User getUserByName(String userName) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE name = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userName);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User(
                                resultSet.getInt("id"),
                                resultSet.getString("userName"),
                                resultSet.getString("name"),
                                resultSet.getString("password")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public void clearDatabase() {
        try {

            String deleteBookingQuery = "DELETE FROM booking WHERE user_id IN (SELECT id FROM users)";
            try (PreparedStatement bookingStatement = connection.prepareStatement(deleteBookingQuery)) {
                bookingStatement.executeUpdate();
            }


            String deleteUserQuery = "DELETE FROM users";
            try (PreparedStatement userStatement = connection.prepareStatement(deleteUserQuery)) {
                userStatement.executeUpdate();
                System.out.println("Database cleared successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DatabaseManager db=new DatabaseManager();
        db.clearDatabase();
    }

    public void updateUserIfExists(String userName, String newPassword, double newPrice, String newDescription) {
        try {
            User existingUser = getUserByName(userName);

            if (existingUser != null) {
                // User exists, update the user information
                String updateQuery = "UPDATE users SET password = ?, price = ?, description = ? WHERE name = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setString(2, newPassword);
                    updateStatement.setDouble(3, newPrice);
                    updateStatement.setString(4, newDescription);
                    updateStatement.setString(1, userName);
                    updateStatement.executeUpdate();

                    System.out.println("User information updated for: " + userName);
                }
            } else {
                System.out.println("User not found. Cannot update information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
