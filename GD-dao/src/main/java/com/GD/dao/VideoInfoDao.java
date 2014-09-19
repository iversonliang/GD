package com.GD.dao;

import java.util.List;

import com.GD.model.VideoInfo;
import com.GD.model.WPPosts;

public interface VideoInfoDao {
	
	public List<WPPosts> listWPPosts();
	
	public List<String> listLabel(int id);
	
	public VideoInfo get(int id);
	
	public List<VideoInfo> listVideoInfo();
	
	public boolean add(VideoInfo videoInfo);

}
