package com.GD.web.vo;

import java.util.Date;

public class CommentVO {
	private int commentId;
	private int videoId;
	private String videoName;
	private int videoSourceType;
	private String content;
	private int userId;
	private String nickname;
	private int replyUserId;
	private String headImg;
	private String replyNickname;
	private String replyContent;
	private String deployTimeTips;
	private Date posttime;

	public int getVideoSourceType() {
		return videoSourceType;
	}

	public void setVideoSourceType(int videoSourceType) {
		this.videoSourceType = videoSourceType;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getReplyUserId() {
		return replyUserId;
	}

	public void setReplyUserId(int replyUserId) {
		this.replyUserId = replyUserId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getReplyNickname() {
		return replyNickname;
	}

	public void setReplyNickname(String replyNickname) {
		this.replyNickname = replyNickname;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getDeployTimeTips() {
		return deployTimeTips;
	}

	public void setDeployTimeTips(String deployTimeTips) {
		this.deployTimeTips = deployTimeTips;
	}

}
