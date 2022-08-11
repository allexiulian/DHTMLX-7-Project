package com.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.bean.Employee;
import com.bean.User;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.service.UserService;
import com.util.JsonToStringUtil;
import com.util.LocalDateDeserializer;

public class UserServiceImpl implements UserService{
	
	private final UserDao userDao = new UserDaoImpl();

	@Override
	public Optional<User> getByUserNameAndPassword(HttpServletRequest req) throws JsonSyntaxException, IOException {
			GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
			gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
			User bean = (User) gsonBuilder.create().fromJson(JsonToStringUtil.format(req),User.class);
			
			return userDao.findByUserNameAndPassword(bean.getUserName(), bean.getPassword());
		}
}