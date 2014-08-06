package com.GD.dao;

import java.util.List;

import com.GD.model.InviteCode;

public interface InviteCodeDao {

	public boolean add(InviteCode inviteCode);

	public InviteCode get(String inviteCodeId);
	
	public boolean use(int userId, String inviteCodeId);
	
	public int count();
	
	public List<InviteCode> list(int start, int size);
}
