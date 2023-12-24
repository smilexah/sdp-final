package com.sdu.edu.kz.booking;

import com.sdu.edu.kz.db.ConnectionDBProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/bookHotel")
public class BookHotel extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = 0;
        int hotelId = 0;
        int roomId = 0;
        double totalPrice = 0;
        try {
            String userIdStr = request.getParameter("userId"); // Retrieve user ID as string
            if (userIdStr != null && !userIdStr.isEmpty()) {
                userId = Integer.parseInt(userIdStr); // Parse only if not null or empty
            }

            String hotelIdStr = request.getParameter("hotelId");
            if (hotelIdStr != null && !hotelIdStr.isEmpty()) {
                hotelId = Integer.parseInt(hotelIdStr);
            }

            String roomIdStr = request.getParameter("roomId");
            if (roomIdStr != null && !roomIdStr.isEmpty()) {
                roomId = Integer.parseInt(roomIdStr);
            }
            String totalPriceStr = request.getParameter("totalPrice");
            if (totalPriceStr != null && !totalPriceStr.isEmpty()) {
                totalPrice = Integer.parseInt(totalPriceStr);
            }
        } catch (NumberFormatException e) {
            // Handle the case where the string cannot be parsed into an integer
            e.printStackTrace();
            // Optionally, redirect to an error page or set an error message in the request
        }

        String checkInDate = request.getParameter("cidate");
        String checkOutDate = request.getParameter("codate");

        boolean bookingSuccessful = bookHotel(userId, hotelId, roomId, checkInDate, checkOutDate, totalPrice);

        // Redirect to a confirmation page or an error page based on the booking result
        if (bookingSuccessful) {
            response.sendRedirect("bookingConfirmation.jsp");
        } else {
            response.sendRedirect("bookingError.jsp");
        }
    }

    private boolean bookHotel(int userId, int hotelId, int roomId, String checkInDate, String checkOutDate, double totalPrice) {
        try {
            Connection connection = ConnectionDBProvider.getConnection();
            String sql = "INSERT INTO bookings (user_id, hotel_id, room_id, check_in_date, check_out_date, total_price) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, hotelId);
            preparedStatement.setInt(3, roomId);
            preparedStatement.setString(4, checkInDate);
            preparedStatement.setString(5, checkOutDate);
            preparedStatement.setDouble(6, totalPrice);

            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
