package com.sdu.edu.kz.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/creditCard")
public class CreditCardPayment extends HttpServlet implements PaymentSystem {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double amount = Double.parseDouble(request.getParameter("amount"));
        processPayment(amount);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Payment Received</h2>");
        out.println("<p>{}</p>");
        out.println("</body></html>");
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Payment was received from credit card in amount: " + amount / 450 + " USD");
    }
}
