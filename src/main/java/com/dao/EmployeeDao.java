package com.dao;

import java.util.List;
import java.util.Optional;

import com.bean.Employee;

public interface EmployeeDao {
	
	List<Employee> getAllEmployees();

	boolean save(Employee bean);

	boolean detele(Long id);

	Optional<Employee> findByEmailAndPassword(String userName, String password);

	Optional<Employee> findByEmpID(Long id);
	
}
