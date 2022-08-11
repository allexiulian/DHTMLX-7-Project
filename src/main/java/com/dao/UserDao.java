package com.dao;

import java.util.Optional;

import com.bean.User;

public interface UserDao {
	
	Optional<User> findByUserNameAndPassword(String userName, String password);
}
