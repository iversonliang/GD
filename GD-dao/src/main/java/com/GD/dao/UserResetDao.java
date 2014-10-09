package com.GD.dao;

public interface UserResetDao {

	public boolean add(int userId, String code);

	public int get(String code);

	public boolean del(String code);

}
