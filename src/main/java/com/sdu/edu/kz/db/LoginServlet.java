package com.sdu.edu.kz.db;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (authenticate(email, password)) {
            String userRole = getUserRole(email); // Implement this method to retrieve the user role from your database
            String userFirstName = getUserFirstName(email);

            request.getSession().setAttribute("LoggedInRole", userRole);
            request.getSession().setAttribute("userFirstName", userFirstName);

            response.sendRedirect("/index.jsp");

        } else {
            request.setAttribute("message", "Invalid email or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean authenticate(String email, String password) {
        boolean isAuthenticated = false;

        try (Connection connection = ConnectionDBProvider.getConnection()) {
            String sql = "SELECT password_hash, salt FROM users WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String storedHashedPassword = resultSet.getString("password_hash");
                    byte[] salt = resultSet.getBytes("salt");

                    // Hash the provided password using the same salt
                    String hashedPassword = hashPassword(password, salt);

                    // Compare the stored hashed password with the newly hashed password
                    isAuthenticated = hashedPassword.equals(storedHashedPassword);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log this exception
        }

        return isAuthenticated;
    }

    private String hashPassword(String password, byte[] salt) {
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }

    private String getUserRole(String email) {
        String role = null;

        try (Connection connection = ConnectionDBProvider.getConnection()) {
            String sql = "SELECT role FROM users WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    role = resultSet.getString("role");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log and handle the exception properly
        }

        return role;
    }

    private String getUserFirstName(String email) {
        String first_name = "";

        try (Connection connection = ConnectionDBProvider.getConnection()) {
            String sql = "SELECT first_name FROM users WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    first_name = resultSet.getString("first_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log and handle the exception properly
        }

        return first_name;
    }
}
