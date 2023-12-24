package com.sdu.edu.kz.booking;

import com.google.gson.Gson;
import com.sdu.edu.kz.db.ConnectionDBProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addbooking")
public class AddBooking extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract form data
        String location = request.getParameter("loction");
        int adults = Integer.parseInt(request.getParameter("adults"));
        int children = Integer.parseInt(request.getParameter("children"));
        String checkInDate = request.getParameter("cidate");
        String checkOutDate = request.getParameter("codate");

        // Query the database for hotel information based on the form data
        List<Hotel> hotelList = yourDatabaseQuery(location, adults, children, checkInDate, checkOutDate);

        // Store the hotel list in a request attribute
        request.setAttribute("hotelList", hotelList);

        // Forward to a JSP page to display the hotels
        RequestDispatcher dispatcher = request.getRequestDispatcher("/booking.jsp");
        dispatcher.forward(request, response);
    }

    // Implement the method to query the database here
    private List<Hotel> yourDatabaseQuery(String location, int adults, int children, String checkInDate, String checkOutDate) {
        List<Hotel> hotelList = new ArrayList<>();

        // Use JDBC or an ORM (e.g., Hibernate) to interact with the database
        try {
            Connection connection = ConnectionDBProvider.getConnection(); // Implement this method to establish a database connection

            // Construct and execute the SQL query based on form data
            String sql = "SELECT * FROM hotels WHERE location = ?"; // Add more conditions as needed
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, location);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Process the query result and populate the hotelList
            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("hotel_id"));
                hotel.setName(resultSet.getString("name"));
                hotel.setAddress(resultSet.getString("address"));
                hotel.setPhoneNumber(resultSet.getString("phone_number"));
                hotel.setDescription(resultSet.getString("description"));
                // Retrieve other hotel attributes as needed
                hotelList.add(hotel);
            }

            // Close database resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotelList;
    }
}