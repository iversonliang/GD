package com.GD.dao;

import java.util.List;

import com.GD.model.LikeVideo;

public interface LikeVideoDao {
	
	public boolean add(LikeVideo likeVideo);
	
	public List<LikeVideo> listByUser(int userId, int start, int size);
	
	public boolean isLike(int userId, int videoId);
	
	public int count(int userId);
	
	public boolean delete(int userId, int videoId);
	
}
