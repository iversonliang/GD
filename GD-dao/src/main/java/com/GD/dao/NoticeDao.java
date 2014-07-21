package com.GD.dao;

import java.util.List;

import com.GD.model.Notice;

public interface NoticeDao {

	public boolean add(Notice notice);
	
	public int count(int userId);
	
	public List<Notice> list(int userId, int start, int size);
}
