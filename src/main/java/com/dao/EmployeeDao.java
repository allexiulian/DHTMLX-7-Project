package com.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.bean.Employee;
import com.bean.User;

public interface EmployeeDao {
	
	List<Employee> getAllEmployees();

	boolean save(Employee bean);

	boolean detele(Long id);

	
}
