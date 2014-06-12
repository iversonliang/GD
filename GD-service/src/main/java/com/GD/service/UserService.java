package com.GD.service;

import java.util.List;

import com.GD.model.User;
import com.GD.type.UserStatusType;

public interface UserService {

	public boolean add(User user);

	public boolean waitActivate(int userId, String code);

	public boolean updateStatus(int userId, UserStatusType type);

	public boolean activate(String code);

	public User get(String username, String password);
	
	public User get(String username);

	public User get(int userId);

	/**
	 * 注册时检查用户名是否合法
	 * 
	 * @param username
	 * @return
	 */
	public boolean checkUsername(String username);

	/**
	 * 注册时检查邮箱是否合法
	 * 
	 * @param email
	 * @return
	 */
	public boolean checkEmail(String email);

	public void checkUser(int userId);

	public int count();

	public List<User> list(int start, int size);
}
