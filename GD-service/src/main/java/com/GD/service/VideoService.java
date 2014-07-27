package com.GD.service;

import java.util.List;

import com.GD.model.Video;
import com.GD.type.HomeType;
import com.GD.type.SortType;
import com.GD.type.StatusType;
import com.GD.type.TimeLimitType;
import com.GD.type.VideoGradeType;
import com.GD.type.VideoSourceType;
import com.GD.type.VideoType;

public interface VideoService {

	public boolean add(Video video);
	
	public Video get(int videoId);
	
	public void checkVideo(int videoId);
	
	public int countAll(HomeType homeType, TimeLimitType timeLimitType, boolean showDel);

	public int count(StatusType statusType, VideoType videoType, HomeType homeType, VideoGradeType videoGradeType, VideoSourceType videoSourceType, TimeLimitType timeLimitType, String keyword, boolean showDel);
	
	public List<Video> listAll(HomeType homeType, SortType sortType, TimeLimitType timeLimitType, boolean showDel, int start, int size);
	
	public int countByUser(int userId);
	
	public List<Video> list(int userId, int start, int size);

	public List<Video> list(StatusType statusType, VideoType videoType, HomeType homeType, VideoGradeType videoGradeType, VideoSourceType videoSourceType, SortType sortType, TimeLimitType timeLimitType, boolean showDel, String keyword, int start, int size);
	
	public boolean play(int videoId, String sessionId);
	
	public boolean love(int videoId);
	
	public boolean incrComment(int videoId);

	public boolean del(int userId, int videoId);
	
	public boolean unDel(int userId, int videoId);
	
	public boolean setHomeType(int userId, int videoId, HomeType homeType, int indexNum);
	
	public boolean delHomeType(int userId, int videoId);
	
	public boolean updateGradeType(int userId, int videoId, VideoGradeType gradeType);
}
