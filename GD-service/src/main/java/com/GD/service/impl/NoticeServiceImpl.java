package com.GD.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.NoticeDao;
import com.GD.model.Notice;
import com.GD.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public boolean add(Notice notice) {
		return noticeDao.add(notice);
	}

	@Override
	public int count(int userId) {
		return noticeDao.count(userId);
	}

	@Override
	public List<Notice> list(int userId, int start, int size) {
		return noticeDao.list(userId, start, size);
	}

}
