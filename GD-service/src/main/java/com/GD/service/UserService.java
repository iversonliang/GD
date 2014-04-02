package com.GD.service;

import com.GD.model.User;
import com.GD.type.UserStatusType;

public interface UserService {

	public boolean add(User user);
	
	public boolean waitActivate(int userId, String code);
	
	public boolean updateStatus(int userId, UserStatusType type);
	
	public boolean activate(String code);
	
	public User get(String username, String password);
}
