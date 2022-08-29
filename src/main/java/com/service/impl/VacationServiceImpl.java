package com.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

public class VacationServiceImpl implements VacationService{
	
	private final VacationDao vacationDao = new VacationDaoImpl();

	@Override
	public String getJson(Long id) {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		return gsonBuilder.create().toJson(vacationDao.findAllByEmployee(id));
	}
	
	@Override
	public String getJsonWithPending(Long id) {
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        return gsonBuilder.create().toJson(vacationDao.findAllEmployeeWithPending(id));
	}

	@Override
	public int create(HttpServletRequest req) throws JsonSyntaxException, IOException {	
		GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
		Vacation bean = (Vacation) gsonBuilder.create().fromJson(JsonToStringUtil.format(req),Vacation.class);
		bean.setStatus("Pending");
		 return vacationDao.save(bean) ? HttpServletResponse.SC_ACCEPTED : HttpServletResponse.SC_BAD_REQUEST;
	}

	@Override
	public void accept(String action, HttpServletRequest req) throws IOException {
		Long id = JsonToStringUtil.formatLong(req);
		vacationDao.acceptVacation(action, id);

	}

	@Override
	public void decline(String action, HttpServletRequest req) throws IOException {
		Long id = JsonToStringUtil.formatLong(req);
		vacationDao.declineVacation(action, id);
	}

	

}
