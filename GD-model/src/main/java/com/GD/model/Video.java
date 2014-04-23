package com.GD.model;

import java.util.Date;

public class Video {

	private int videoId;
	/** 视频名称 */
	private String name;
	/** 视频描述 */
	private String description;
	/** 视频地址 */
	private String url;
	/** 视频播放次数 */
	private int play;
	/** 视频评论次数 */
	private int comments;
	/** 视频喜爱人数 */
	private int love;
	/** 视频作者ID */
	private int userId;
	/** 视频作者名称 */
	private String username;
	/** 视频发表时间 */
	private Date posttime;
	/** 视频是否删除 */
	private boolean del;
	/** 视频状态 */
	private int status;
	/** 视频类型 */
	private int videoType;
	/** 视频等级 */
	private int videoGradeType;
	/** 视频标签 */
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getVideoType() {
		return videoType;
	}

	public void setVideoType(int videoType) {
		this.videoType = videoType;
	}

	public int getVideoGradeType() {
		return videoGradeType;
	}

	public void setVideoGradeType(int videoGradeType) {
		this.videoGradeType = videoGradeType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPlay() {
		return play;
	}

	public void setPlay(int play) {
		this.play = play;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getLove() {
		return love;
	}

	public void setLove(int love) {
		this.love = love;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public boolean isDel() {
		return del;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

}
