package com.service.impl;

import java.io.IOException;
import java.time.LocalDate;

import com.bean.Employee;
import com.bean.Vacation;
import com.dao.VacationDao;
import com.dao.impl.VacationDaoImpl;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.LongSerializationPolicy;
import com.service.VacationService;
import com.util.JsonToStringUtil;
import com.util.LocalDateDeserializer;
import com.util.LocalDateSerializer;
import javax.servlet.http.HttpServletRequest;


public class VacationServiceImpl implements VacationService{
	
	private final VacationDao vacatiodao = new VacationDaoImpl();

	@Override
	public String getJson(Long id) {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		return gsonBuilder.create().toJson(vacatiodao.findAllByEmployee(id));
	}

	@Override
	public boolean create(HttpServletRequest req) throws JsonSyntaxException, IOException {	
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
		Vacation bean = (Vacation) gsonBuilder.create().fromJson(JsonToStringUtil.format(req),Vacation.class);
		return vacatiodao.save(bean);
	}

}
