package com.GD.dao;

import java.util.List;

import com.GD.model.LikeVideo;

public interface LikeVideoDao {
	
	public boolean add(LikeVideo likeVideo);
	
	public List<LikeVideo> listByUser(int userId);

}
