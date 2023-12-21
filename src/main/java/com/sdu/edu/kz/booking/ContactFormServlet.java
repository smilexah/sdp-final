package com.sdu.edu.kz.booking;

import com.sdu.edu.kz.db.ConnectionDBProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/contactForm")
public class ContactFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().append("Served at: ").append(req.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try{
            String first_name = req.getParameter("first_name");
            String email = req.getParameter("email");
            String message = req.getParameter("message");

            Connection connection = ConnectionDBProvider.getConnection();
            PreparedStatement ps;

            String sql = "insert into messages (first_name, email, message) values(?,?,?)";

            Class.forName("org.postgresql.Driver");
            ps = connection.prepareStatement(sql);
            ps.setString(1, first_name);
            ps.setString(2, email);
            ps.setString(3, message);
            ps.executeUpdate();

            out.println("<script type=\"text/javascript\">");
            out.println("alert('Form Submitted Successfully !!');");
            out.println("location='contact.jsp';");
            out.println("</script>");
        }
        catch (SQLException | ClassNotFoundException ex) {
            out.println(ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
