package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.util.HttpHandler;

public class AdminServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpHandler.handle(res);
		HttpSession session = req.getSession(false);
		if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user == null)   {
            	System.out.println("Bag");
            	res.sendRedirect("logout");
            }else {
            	req.getRequestDispatcher("pages/admin.html").forward(req, res);
            }
         }else {
        	 res.sendRedirect("logout");
         }
		
	}
}
