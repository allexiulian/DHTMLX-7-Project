package com.servlet;

import java.io.IOException;

import com.service.EmployeeService;
import com.service.impl.EmployeeServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteEmployeeServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private final EmployeeService employeeservice = new EmployeeServiceImpl();
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		if (employeeservice.delete(req)) {
			res.setStatus(HttpServletResponse.SC_ACCEPTED);
		} else {
			
			res.setStatus(HttpServletResponse.SC_CONFLICT);
		}
	}

}
