package com.GD.service;

import java.util.List;

public interface LikeVideoService {

	public boolean add(int userId, int videoId);
	
	public boolean isLiked(int userId, int videoId);
	
	public boolean delete(int userId, int videoId);
	
	public int count(int userId);
	
	public List<Integer> listLikeVideoId(int userId, int start, int size);
}
