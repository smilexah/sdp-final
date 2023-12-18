package com.sdu.edu.kz.db;

import java.sql.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("userName");
        String password = request.getParameter("password");

        try (Connection connection = ConnectionDBProvider.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password); // In a real application, compare hashed passwords
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    // User exists, handle login success
                    request.setAttribute("successMessage", "Login Successful!");
                    // Redirect to a welcome or user profile page
                    request.getRequestDispatcher("/main.jsp").forward(request, response); // Replace with your welcome page
                } else {
                    // User does not exist, handle login failure
                    request.setAttribute("message", "Invalid username or password.");
                    request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            request.setAttribute("message", "Login failed: " + e.getMessage());
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }
    }
}
