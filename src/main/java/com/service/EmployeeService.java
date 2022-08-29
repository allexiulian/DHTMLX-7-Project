package com.service;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.bean.Employee;
import com.google.gson.JsonSyntaxException;



public interface EmployeeService {

	String getJson();

	boolean create(HttpServletRequest req) throws JsonSyntaxException, IOException;

	boolean delete(HttpServletRequest req) throws IOException;
	
	Optional<Employee> findByEmailAndPassword(HttpServletRequest req) throws JsonSyntaxException, IOException;

	Optional<Employee> getByID(Long id);

}
