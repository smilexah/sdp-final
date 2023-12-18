package com.sdu.edu.kz.db;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/auth/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("userName");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String reTypePassword = request.getParameter("reTypePassword");

        // Check if passwords match
        if (!password.equals(reTypePassword)) {
            request.setAttribute("error", "Passwords do not match.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        try (Connection connection = ConnectionDBProvider.getConnection()) {
            String sql = "INSERT INTO users (username, name, password) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, name);
                statement.setString(3, password); // Hash the password in a real application
                statement.executeUpdate();

                // Redirect to login page or show success message
                request.setAttribute("message", "Registration successful! Please login.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }
}
