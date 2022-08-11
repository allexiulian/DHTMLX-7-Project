package com.servlet;

import java.io.IOException;

import com.service.VacationService;
import com.service.impl.VacationServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class VacationServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;


	private final VacationService vacationService = new VacationServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {	
		res.setContentType("text/json");
		Long id = Long.parseLong(req.getParameter("id"));
		res.getWriter().println(vacationService.getJson(id));

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {	
		if (vacationService.create(req)) {
			res.setStatus(HttpServletResponse.SC_ACCEPTED);
		} else {
			res.setStatus(HttpServletResponse.SC_CONFLICT);
		}
	
}

}
