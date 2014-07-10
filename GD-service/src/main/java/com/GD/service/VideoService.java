package com.GD.service;

import java.util.List;

import com.GD.model.Video;
import com.GD.type.StatusType;
import com.GD.type.VideoGradeType;
import com.GD.type.VideoSourceType;
import com.GD.type.VideoType;

public interface VideoService {

	public boolean add(Video video);
	
	public Video get(int videoId);
	
	public void checkVideo(int videoId);
	
	public int countAll();

	public int count(StatusType statusType, VideoType videoType, VideoGradeType videoGradeType, VideoSourceType videoSourceType, String name, String label);
	
	public List<Video> listAll(int start, int size);
	
	public int count(int userId);
	
	public List<Video> list(int userId, int start, int size);

	public List<Video> list(StatusType statusType, VideoType videoType, VideoGradeType videoGradeType, VideoSourceType videoSourceType, String name, String label, int start, int size);
	
	public boolean play(int videoId);
	
	public boolean love(int videoId);
	
	public boolean incrComment(int videoId);

	public boolean del(int userId, int videoId);
}
