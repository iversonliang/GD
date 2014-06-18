package com.GD.dao;

import java.util.List;

import com.GD.model.Video;

public interface VideoDao {

	public boolean add(Video video);

	public Video get(int videoId);
	
	public boolean del(int videoId);

	public int count(int userId);
	
	public int count(int status, int videoType, int videoGradeType, int videoSourceType, String name, String label);

	public List<Video> list(int status, int videoType, int videoGradeType, int videoSourceType, String name, String label, int start, int size);
	
	public List<Video> list(int userId, int start, int size);

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
}
