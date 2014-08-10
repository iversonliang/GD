package com.GD.dao;

import java.util.List;

import com.GD.model.Apply;

public interface ApplyDao {
	
	public boolean add(Apply apply);
	
	public boolean pass(int applyId, String inviteCodeId);
	
	public boolean activate(int userId);
	
	public int count();
	
	public List<Apply> list(int start, int size);

	public Apply getByUserId(int userId);
}
