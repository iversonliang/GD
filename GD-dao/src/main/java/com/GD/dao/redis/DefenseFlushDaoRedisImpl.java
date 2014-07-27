package com.GD.dao.redis;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.DefenseFlushDao;
import com.GD.redis.Redis;
import com.GD.util.DateUtil;
import com.GD.util.RedisKey;

@Repository
public class DefenseFlushDaoRedisImpl implements DefenseFlushDao{

	@Autowired
	private Redis redis;
	
	private int seconds = 60;
	
	@Override
	public boolean add(String sessionId, int videoId) {
		String key = RedisKey.getDefenseFlush(sessionId, videoId);
		Long result = this.redis.setnx(key, DateUtil.date2String(new Date()));
		if (result != null && result > 0) {
			this.redis.expire(key, seconds);
			return true;
		}
		return false;
	}

	@Override
	public boolean isExist(String sessionId, int videoId) {
		String key = RedisKey.getDefenseFlush(sessionId, videoId);
		return this.redis.exists(key);
	}
	
}
