package com.GD.dao;

public interface DefenseFlushDao {
	
	public boolean add(String sessionId, int videoId);
	
	public boolean isExist(String sessionId, int videoId);

}
