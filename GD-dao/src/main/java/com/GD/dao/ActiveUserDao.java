package com.GD.dao;

import java.util.Date;
import java.util.List;

import com.GD.model.User;

public interface ActiveUserDao {

	public boolean add(User user, Date date);
	
	public boolean remove(User user);
	
	public boolean update(User newUser, User oldUser);
	
	public List<User> list(int start, int size);
}
