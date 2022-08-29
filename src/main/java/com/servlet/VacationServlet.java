package com.servlet;

import java.io.IOException;

import com.service.VacationService;
import com.service.impl.VacationServiceImpl;
import com.util.HttpHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class VacationServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;


	private final VacationService vacationService = new VacationServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {	
		HttpHandler.handle(res);
		String filter = req.getParameter("filter");
		res.setContentType("text/json");
		
		if(filter!=null) {
			try {
				Long id = Long.parseLong(req.getParameter("id"));
				res.getWriter().println(vacationService.getJsonWithPending(id));
			} catch (Exception e) {
				res.sendRedirect("login");
			}
		}else {
			try {
				Long id = Long.parseLong(req.getParameter("id"));
				res.getWriter().println(vacationService.getJson(id));
			} catch (Exception e) {
				res.sendRedirect("login");
			}
		}
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {	
		HttpHandler.handle(res);
		String action = req.getParameter("action");
		int status = 0;
		System.out.println(action);
		switch (action) {
		
		case "add"  -> {
			status = vacationService.create(req);			
			break;
		}
		
		
		case "Accepted" -> {
			vacationService.accept(action, req);				
				break;
			
		}
		case "Declined" -> {
			vacationService.decline(action, req);
				break;		
			}
		default -> {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			break;
		}		
	}
		if (status == 400) {
			res.getWriter().write("Failed to create vacation");
			res.setStatus(status);
		}
		if (status == 409) {
			res.getWriter().write("Invalid vacation period!");
			res.setStatus(status);
		}
		
	}

}
