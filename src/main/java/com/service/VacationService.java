package com.service;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonSyntaxException;



public interface VacationService {

	String getJson(Long id);

	int create(HttpServletRequest req) throws JsonSyntaxException, IOException;

	void accept(String action, HttpServletRequest req) throws IOException;

	void decline(String action, HttpServletRequest req) throws IOException;

	String getJsonWithPending(Long id);

	

}
