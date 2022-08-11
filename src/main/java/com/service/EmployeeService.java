package com.service;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.bean.User;
import com.google.gson.JsonSyntaxException;



public interface EmployeeService {

	String getJson();

	boolean create(HttpServletRequest req) throws JsonSyntaxException, IOException;

	boolean delete(HttpServletRequest req) throws IOException;


}
