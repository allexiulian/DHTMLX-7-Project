package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.service.CsvService;
import com.service.impl.CsvServiceImpl;
import com.util.HttpHandler;

@MultipartConfig
public class CsvServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private static final Logger Log = Logger.getLogger(CsvServlet.class.getName());
	
	private final CsvService csvService = new CsvServiceImpl();
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	Log.info("Request to download csv");
	HttpHandler.handle(res);
	HttpSession session = req.getSession(false);
	if(session != null) {
		res.setHeader("Content-disposition","attachment; filename=data.csv");
		ServletOutputStream out = res.getOutputStream();
		for(String elem : csvService.getAllVacations()) {
			out.write(elem.getBytes());
		}
	}else {
		Log.info("Invalid user");
		System.out.println("Invalid user");
	}
	
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	Part filePart = req.getPart("file");
	InputStream fileInputStream = filePart.getInputStream();
	String status = csvService.upload(fileInputStream);
	if(status.contains("Duplicate") || status.contains("Invalid")) {
		res.setStatus(HttpServletResponse.SC_CONFLICT);
		res.getWriter().write(status);
	}
	
}

}
