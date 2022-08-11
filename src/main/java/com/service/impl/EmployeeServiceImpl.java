package com.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bean.Employee;
import com.bean.User;
import com.dao.EmployeeDao;
import com.dao.impl.EmployeeDaoImpl;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.LongSerializationPolicy;
import com.service.EmployeeService;
import com.util.JsonToStringUtil;
import com.util.LocalDateDeserializer;
import com.util.LocalDateSerializer;

import javax.servlet.http.HttpServletRequest;

public class EmployeeServiceImpl implements EmployeeService{

	private final EmployeeDao employeedao = new EmployeeDaoImpl();
	
	@Override
	public String getJson() {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		return gsonBuilder.create().toJson(employeedao.getAllEmployees());
	}

	@Override
	public boolean create(HttpServletRequest req) throws JsonSyntaxException, IOException {
		Employee bean = new Employee();
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
		bean = (Employee) gsonBuilder.create().fromJson(JsonToStringUtil.format(req),Employee.class);
		System.out.println(bean);
		return employeedao.save(bean);
	}

	@Override
	public boolean delete(HttpServletRequest req) throws IOException  {
		Long id = Long.parseLong(req.getReader().lines().collect(Collectors.joining()).replaceAll("\\D", ""));
		return employeedao.detele(id);
	}

	

}
