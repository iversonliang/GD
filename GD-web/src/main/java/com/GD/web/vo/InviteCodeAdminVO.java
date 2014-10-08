package com.GD.web.vo;

import java.util.Date;

public class InviteCodeAdminVO {

	private String inviteCodeId;
	private int useUserId;
	private Date posttime;
	private Date useTime;
	private int status;
	private String useUsername;

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUseUsername() {
		return useUsername;
	}

	public void setUseUsername(String useUsername) {
		this.useUsername = useUsername;
	}

}
