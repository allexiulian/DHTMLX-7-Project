package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.service.EmployeeService;
import com.service.impl.EmployeeServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewEmployeeServlet extends HttpServlet{
	
	private final EmployeeService employeeservice = new EmployeeServiceImpl();
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("text/json");
		res.getWriter().print(employeeservice.getJson());		
	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		if(employeeservice.create(req)) {
			res.setStatus(HttpServletResponse.SC_ACCEPTED);
		} else {
			
            res.setStatus(HttpServletResponse.SC_CONFLICT);
        }
       
		

	}

}
