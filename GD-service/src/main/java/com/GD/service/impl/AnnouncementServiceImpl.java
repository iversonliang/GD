package com.GD.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.AnnouncementDao;
import com.GD.model.Announcement;
import com.GD.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
	
	@Autowired
	private AnnouncementDao announcementDao;

	@Override
	public boolean add(String title, String content, String imgUrl) {
		Announcement announcement = new Announcement();
		announcement.setContent(content);
		announcement.setImgUrl(imgUrl);
		announcement.setPosttime(new Date());
		announcement.setTitle(title);
		return announcementDao.add(announcement);
	}

	@Override
	public int count() {
		return announcementDao.count();
	}

	@Override
	public List<Announcement> list(int start, int size) {
		return announcementDao.list(start, size);
	}

	@Override
	public boolean delete(int announcementId) {
		return announcementDao.delete(announcementId);
	}

}
