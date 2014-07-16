package com.GD.service;

import java.util.List;

import com.GD.model.Announcement;

public interface AnnouncementService {
	
	public boolean add(String title, String content, String imgUrl);
	
	public boolean delete(int announcementId);
	
	public int count();
	
	public List<Announcement> list(int start, int size);

}
