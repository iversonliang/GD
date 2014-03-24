package com.GD.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.UserDao;
import com.GD.model.User;
import com.GD.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public boolean add(User user) {
		return userDao.add(user);
	}

	@Override
	public User get(String username, String password) {
		return userDao.get(username, password);
	}

}
