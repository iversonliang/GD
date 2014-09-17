package com.GD.service;

import com.GD.model.LastReadMessage;
import com.GD.type.MessageType;

public interface LastReadMessageService {

	public boolean add(int userId, int num, MessageType messageType);
	
	public LastReadMessage get(int userId);
}
