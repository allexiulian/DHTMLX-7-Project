package com.dao;

import java.util.List;

import com.bean.Vacation;
import com.google.gson.JsonElement;

public interface VacationDao {

	List<Vacation> findAllByEmployee(Long id);

	boolean save(Vacation bean);
	
	List<Vacation> getAllVacations();

	boolean saveAll(List<Vacation> result);

}
