package com.GD.dao;

import java.util.List;

import com.GD.model.Video;

public interface VideoDao {
	
	public boolean add(Video video);
	
	public Video get(int videoId);
	
	public int count(int status, int videoType, int videoGradeType, String name, String label);
	
	public List<Video> list(int status, int videoType, int videoGradeType, String name, String label, int start, int size);
}
