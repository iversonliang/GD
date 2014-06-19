package com.GD.dao;

import java.util.List;

import com.GD.model.User;

public interface UserDao {

	public long add(User user);

	public User get(int userId);

	public User getByUsername(String username);

	public int count();

	public List<User> listByPosttime(int start, int size);

	public User get(String username, String password);

	public User getByEmail(String email);

	public boolean updateStatus(int userId, int status);

	public boolean update(User user);
	
	public boolean incrVideo(int userId);

	/**
	 * 更新用户头像地址
	 * 
	 * @param userId
	 * @param url
	 * @return
	 */
	public boolean updateHeadImg(int userId, String url);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean updatePassword(int userId, String password);
}
