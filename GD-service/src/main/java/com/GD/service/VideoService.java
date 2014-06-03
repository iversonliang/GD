package com.GD.service;

import java.util.List;

import com.GD.model.Video;
import com.GD.type.StatusType;
import com.GD.type.VideoGradeType;
import com.GD.type.VideoType;

public interface VideoService {

	public boolean add(int userId, String name, String description, String label, String url, VideoType videoType);

	public int count(StatusType statusType, VideoType videoType, VideoGradeType videoGradeType, String name, String label);

	public List<Video> list(StatusType statusType, VideoType videoType, VideoGradeType videoGradeType, String name, String label, int start, int size);
	
	public boolean play(int videoId);
	
	public boolean love(int videoId);
	
	public boolean addCommont(int videoId, String comment);

	public boolean del(int userId, int videoId);
}
