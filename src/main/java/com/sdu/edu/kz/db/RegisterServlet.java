package com.sdu.edu.kz.db;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/auth/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String phone_number = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection connection = ConnectionDBProvider.getConnection()) {
            // Check if username already exists
            if (userEmailExists(email, connection)) {
                request.setAttribute("error", "Email already exists.");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }

            if (userPhoneNumberExists(phone_number, connection)) {
                request.setAttribute("error", "Phone number already exists.");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }

            byte[] salt = getSalt();
            String password_hash = hashPassword(password, salt);
            String role = "user"; // default role

            String sql = "INSERT INTO users (first_name, last_name, phone_number, email, password_hash, salt, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, first_name);
                statement.setString(2, last_name);
                statement.setString(3, phone_number);
                statement.setString(4, email);
                statement.setString(5, password_hash);
                statement.setBytes(6, salt); // Store the salt in the database
                statement.setString(7, role);
                statement.executeUpdate();

                request.setAttribute("message", "Registration successful! Please login.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }

    private boolean userEmailExists(String email, Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

    private boolean userPhoneNumberExists(String phone_number, Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE phone_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phone_number);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

    private byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    private String hashPassword(String password, byte[] salt) {
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }
}
