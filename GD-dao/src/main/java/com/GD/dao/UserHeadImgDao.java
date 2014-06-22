package com.GD.dao;

public interface UserHeadImgDao {

	public boolean add(int userId, String headImg);
	
	public String get(int userId);
}
