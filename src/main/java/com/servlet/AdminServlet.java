package com.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityLayoutServlet;

import com.bean.Employee;
import com.util.HttpHandler;

public class AdminServlet extends VelocityLayoutServlet{
	
	
	@Override
	protected Template handleRequest(HttpServletRequest req, HttpServletResponse res, Context context) {
		
		HttpHandler.handle(res);
		HttpSession session = req.getSession(false);
		String name = null;
		if (session != null) {
			Employee user = (Employee) session.getAttribute("user");
            if (user == null)   {         
            	try {
					res.sendRedirect("logout");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
            }else {
            	String role = (String) session.getAttribute("role");
            	if(role == null || !role.contains("Admin")) {
            		try {
						res.sendRedirect("account");
					} catch (IOException e) {						
						e.printStackTrace();
					}
            	}else {
            		name = user.getName();
            	}
            }
         }else {
        	 try {
					res.sendRedirect("logout");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
         }

		Template template = getTemplate("pages/admin.html");
        context.put("user", name);
        
        return template;
	}
}
