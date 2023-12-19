package com.sdu.edu.kz;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username != null) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h1>Welcome, " + username + "!</h1>");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h1>User not found</h1>");
        }
    }
}
