package com.GD.dao;

import java.util.List;

import com.GD.model.Announcement;

public interface AnnouncementDao {

	public boolean add(Announcement announcement);
	
	public int count();
	
	public boolean delete(int announcementId);
	
	public List<Announcement> list(int start, int size);
	
}
