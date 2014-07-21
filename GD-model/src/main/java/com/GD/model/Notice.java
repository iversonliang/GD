package com.GD.model;

import java.util.Date;

public class Notice {

	private int noticeId;
	private int userId;
	private String imgUrl;
	private int commentId;
	private int videoId;
	private String content;
	private Date posttime;
	private int opUserId;

	public int getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(int opUserId) {
		this.opUserId = opUserId;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

}
