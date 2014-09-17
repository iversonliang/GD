package com.GD.model;

import java.util.Date;

public class VideoInfo {

	private int id;
	private String youkuId;
	private String postName;
	private Date postDate;
	private String postTitle;
	private String label;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYoukuId() {
		return youkuId;
	}

	public void setYoukuId(String youkuId) {
		this.youkuId = youkuId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
