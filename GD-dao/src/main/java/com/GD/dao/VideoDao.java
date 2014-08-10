package com.GD.dao;

import java.util.List;

import com.GD.model.Video;

public interface VideoDao {

	public boolean add(Video video);

	public Video get(int videoId);

	public boolean del(int videoId);
	
	public boolean unDel(int videoId);

	public int countByUser(int userId, int videoSourceType);

	public int count(int status, int videoType, int homeType, int videoGradeType, int videoSourceType, int timeLimitType, String keyword, boolean showDel);

	public List<Video> list(int status, int videoType, int homeType, int videoGradeType, int videoSourceType, int sortType, int timeLimitType, String keyword, boolean showDel, int start, int size);

	public List<Video> list(int userId, int videoSourceType, int start, int size);

	/**
	 * 增加播放次数
	 * 
	 * @param videoId
	 * @return
	 */
	public boolean play(int videoId);

	/**
	 * 增加喜爱次数
	 * 
	 * @param videoId
	 * @return
	 */
	public boolean love(int videoId);

	/**
	 * 增加评论次数
	 * 
	 * @param videoId
	 * @return
	 */
	public boolean commont(int videoId);

	public int getMaxIndexNum(int homeType);

	public Video getByIndexNum(int homeType, int indexNum);

	public boolean updateIndexBetween(int homeType, int start, int end, boolean isIncr);
	
	public boolean updateHomeTypeIndex(int videoId, int homeType, int indexNum);
	
	public boolean updateVideoGradeType(int videoId, int videoGradeType);
}
