package com.GD.dao;

import com.GD.model.User;

public interface UserDao {

	public boolean add(User user);
	
	public boolean get(String username, String password);
	
	public boolean update(User user);
}
