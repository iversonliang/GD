package com.GD.model;

public class Message {

	private int userId;
	private int toMyComments;
	private int replyToMe;
	private int notice;
	private int announcement;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getToMyComments() {
		return toMyComments;
	}

	public void setToMyComments(int toMyComments) {
		this.toMyComments = toMyComments;
	}

	public int getReplyToMe() {
		return replyToMe;
	}

	public void setReplyToMe(int replyToMe) {
		this.replyToMe = replyToMe;
	}

	public int getNotice() {
		return notice;
	}

	public void setNotice(int notice) {
		this.notice = notice;
	}

	public int getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(int announcement) {
		this.announcement = announcement;
	}

}
