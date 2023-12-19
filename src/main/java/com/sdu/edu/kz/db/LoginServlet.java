package com.sdu.edu.kz.db;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("userName");
        String password = request.getParameter("password");

        if (authenticate(username, password)) {
            request.getSession().setAttribute("username", username);
            response.sendRedirect("/main");
        } else {
            request.setAttribute("message", "Invalid username or password.");
            request.getRequestDispatcher("/auth/login.jsp").forward(request, response);
        }
    }

    private boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;

        try (Connection connection = ConnectionDBProvider.getConnection()) {
            String sql = "SELECT password FROM users WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    // Compare the stored password with the provided one
                    isAuthenticated = password.equals(storedPassword); // Simple comparison
                    // For real applications, use hashed passwords and compare accordingly.
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log this exception
        }

        return isAuthenticated;
    }
}
