package com.GD.web.form;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.GD.model.Apply;

public class ApplyForm {

	private String name;
	private String email;
	private String location;
	private String crew;
	private String oupsUrl;

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
	
	public Apply toObj(int userId) {
		Apply apply = new Apply();
		apply.setCrew(StringUtils.defaultIfEmpty(this.getCrew(), ""));
		apply.setEmail(this.getEmail());
		apply.setLocation(this.getLocation());
		apply.setName(this.getName());
		apply.setOupsUrl(StringUtils.defaultIfEmpty(this.getOupsUrl(), ""));
		apply.setPass(false);
		apply.setInviteCodeId("");
		apply.setPosttime(new Date());
		apply.setUserId(userId);
		return apply;
	}

}
