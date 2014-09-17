package com.GD.dao.redis;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.LastReadMessageDao;
import com.GD.model.LastReadMessage;
import com.GD.redis.Redis;
import com.GD.type.MessageType;
import com.GD.util.RedisKey;

@Repository
public class LastReadMessageDaoRedisImpl implements LastReadMessageDao {

	@Autowired
	private Redis redis;

	@Override
	public boolean add(int userId, int num, MessageType messageType) {
		String key = RedisKey.getUserLastReadMessage(userId);
		this.redis.hset(key, messageType.toString(), String.valueOf(num));
		return true;
	}

	@Override
	public LastReadMessage get(int userId) {
		String key = RedisKey.getUserLastReadMessage(userId);
		LastReadMessage message = new LastReadMessage();
		message.setUserId(userId);
		Map<String, String> map = this.redis.hgetAll(key);
		if (map == null) {
			return message;
		}
		String announcement = map.get(MessageType.ANNOUNCEMENT.toString());
		String notice = map.get(MessageType.NOTICE.toString());
		String replyToMe = map.get(MessageType.REPLY_TO_ME.toString());
		String toMyComment = map.get(MessageType.TO_MY_COMMENT.toString());
		message.setAnnouncement(StringUtils.isEmpty(announcement) ? 0 : Integer.valueOf(announcement));
		message.setNotice(StringUtils.isEmpty(notice) ? 0 : Integer.valueOf(notice));
		message.setReplyToMe(StringUtils.isEmpty(replyToMe) ? 0 : Integer.valueOf(replyToMe));
		message.setToMyComments(StringUtils.isEmpty(toMyComment) ? 0 : Integer.valueOf(toMyComment));
		return message;
	}
	
}
