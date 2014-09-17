package com.GD.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.LastReadMessageDao;
import com.GD.model.LastReadMessage;
import com.GD.service.LastReadMessageService;
import com.GD.type.MessageType;

@Service
public class LastMessageServiceImpl implements LastReadMessageService {
	
	@Autowired
	private LastReadMessageDao messageDao;

	@Override
	public boolean add(int userId, int num, MessageType messageType) {
		return messageDao.add(userId, num, messageType);
	}

	@Override
	public LastReadMessage get(int userId) {
		return messageDao.get(userId);
	}

}
