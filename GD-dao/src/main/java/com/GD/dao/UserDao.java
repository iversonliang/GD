package com.GD.dao;

import java.util.List;

import com.GD.model.User;

public interface UserDao {

	public long add(User user);
	
	public User get(int userId);
	
	public int count();
	
	public List<User> list(int start, int size);
	
	public User get(String username, String password);
	
	public boolean updateStatus(int userId, int status);
	
	public boolean update(User user);
}
