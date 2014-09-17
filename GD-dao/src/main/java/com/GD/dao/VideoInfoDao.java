package com.GD.dao;

import java.util.List;

import com.GD.model.WPPosts;

public interface VideoInfoDao {
	
	public List<WPPosts> list();
	
	public List<String> listLabel(int id);

}
