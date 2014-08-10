package com.GD.service;

import java.util.List;

import com.GD.model.InviteCode;

public interface InviteCodeService {

	public boolean create(int num);
	
	public boolean activateUser(int userId, String inviteCodeId);
	
	public int count();
	
	public List<InviteCode> list(int start, int size);
	
	public boolean use(int userId, String inviteCodeId);
	
	public boolean send(String inviteCodeId);
	
	public String random();
}
