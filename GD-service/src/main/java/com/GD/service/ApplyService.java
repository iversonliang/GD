package com.GD.service;

import java.util.List;

import com.GD.model.Apply;

public interface ApplyService {

	public boolean add(Apply apply);
	
	public boolean pass(int applyId);
	
	public boolean activate(int userId);
	
	public boolean isActivate(int userId);
	
	public int count();
	
	public Apply getByUser(int userId);
	
	public List<Apply> list(int start, int size);
	
}
