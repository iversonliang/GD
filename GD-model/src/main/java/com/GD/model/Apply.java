package com.GD.model;

import java.util.Date;

public class Apply {

	private int applyId;
	private String inviteCodeId;
	private int userId;
	private String name;
	private String email;
	private String location;
	private String crew;
	private String oupsUrl;
	private Date posttime;
	private boolean pass;

	public String getInviteCodeId() {
		return inviteCodeId;
	}

	public void setInviteCodeId(String inviteCodeId) {
		this.inviteCodeId = inviteCodeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getOupsUrl() {
		return oupsUrl;
	}

	public void setOupsUrl(String oupsUrl) {
		this.oupsUrl = oupsUrl;
	}

}
