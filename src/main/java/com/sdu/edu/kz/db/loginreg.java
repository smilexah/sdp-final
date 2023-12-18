package com.sdu.edu.kz.db;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/auth/loginreg")
public class loginreg extends HttpServlet {
    public loginreg(){}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAOImplementation();
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String submitType = req.getParameter("submit");
        User user = userDAO.getUser(userName, password);

        if (submitType.equals("login") && user != null && user.getName() != null) {
            req.setAttribute("message", user.getName());
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        } else if (submitType.equals("register")) {
            user.setName(req.getParameter("name"));
            user.setPassword(password);
            user.setUserName(userName);
            userDAO.insertUser(user);
            req.setAttribute("successMessage", "Registration is done, please go to login page!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "User not found, please register your account!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
