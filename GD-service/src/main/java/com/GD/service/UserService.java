package com.GD.service;

import com.GD.model.User;

public interface UserService {

	public boolean add(User user);
	
	public User get(String username, String password);
}
