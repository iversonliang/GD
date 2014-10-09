package com.GD.service;

import java.util.List;

import com.GD.model.User;
import com.GD.type.UserStatusType;

public interface UserService {

	public int add(User user);
	
	public void checkAdmin(int userId);

	public boolean updateStatus(int userId, UserStatusType type);

	public boolean activate(String code);
	
	public boolean applyReset(String email);
	
	public boolean reset(String code, String password);

	public User get(String username, String password);

	public User get(String username);

	public User get(int userId);

	public boolean update(User newUser, User oldUser);

	public boolean incrVideo(int userId);

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

	public List<User> listByPosttime(int start, int size);

	/**
	 * 更新头像
	 * 
	 * @param userId
	 * @param url
	 * @return
	 */
	public boolean updateHeadImg(int userId, String url);

	/**
	 * 更新用户密码
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean updatePassword(int userId, String password);

	/**
	 * 列出活跃用户（按用户最后发表视频时间排序）
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	public List<User> listActiveUser(int start, int size);

	/**
	 * 获取用户头像
	 * 
	 * @param userId
	 * @return
	 */
	public String getHeadImg(int userId);
	
	public boolean updateActiveTime(int userId);
}
