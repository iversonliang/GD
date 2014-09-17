package com.GD.dao;

import com.GD.model.LastReadMessage;
import com.GD.type.MessageType;

public interface LastReadMessageDao {
	
	public boolean add(int userId, int num, MessageType messageType);
	
	public LastReadMessage get(int userId);
	
}
