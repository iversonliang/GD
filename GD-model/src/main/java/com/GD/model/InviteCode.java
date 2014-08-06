package com.GD.model;

import java.util.Date;

public class InviteCode {

	private String inviteCodeId;
	private int useUserId;
	private Date posttime;
	private Date useTime;

	public String getInviteCodeId() {
		return inviteCodeId;
	}

	public void setInviteCodeId(String inviteCodeId) {
		this.inviteCodeId = inviteCodeId;
	}

	public int getUseUserId() {
		return useUserId;
	}

	public void setUseUserId(int useUserId) {
		this.useUserId = useUserId;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

}
