package com.GD.service;

public interface LikeVideoService {

	public boolean add(int userId, int videoId);
	
	public boolean isLiked(int userId, int videoId);
	
}
