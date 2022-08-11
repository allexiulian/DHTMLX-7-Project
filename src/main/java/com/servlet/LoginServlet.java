package com.servlet;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import com.util.HttpHandler;


public class LoginServlet extends HttpServlet{
	
    private static final long serialVersionUID = 1L;

    private final Logger log = Logger.getLogger(LoginServlet.class.getName());
    
    private final UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        log.info("Check if user is already logged");
        HttpHandler.handle(res);
        HttpSession session = req.getSession(false);
        if (session != null) {
            log.info("User is already logged redirecting to admin page");
            res.sendRedirect("admin");
        } else {
            log.info("User is not logged accessing login page");
            req.getRequestDispatcher("pages/login.html").forward(req, res);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        log.info("Processing login details");
        HttpHandler.handle(res);
        HttpSession session = req.getSession(false);
        if (session != null) {
            res.sendRedirect("admin");
        } else {
            Optional<User> admin = userService.getByUserNameAndPassword(req);
            if (admin.isEmpty()) {
                res.getWriter().print("User not found!");
                res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else {
                session = req.getSession(true);
                session.setAttribute("user", admin.get());
                res.sendRedirect("admin");
            }
        }

    }
}
