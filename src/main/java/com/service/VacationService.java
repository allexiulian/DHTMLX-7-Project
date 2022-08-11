package com.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonSyntaxException;



public interface VacationService {

	String getJson(Long id);

	boolean create(HttpServletRequest req) throws JsonSyntaxException, IOException;

}
