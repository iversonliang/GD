package com.GD.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.UserHeadImgDao;
import com.GD.redis.Redis;
import com.GD.util.RedisKey;

@Repository
public class UserHeadImgDaoRedisImpl implements UserHeadImgDao{
	
	@Autowired
	private Redis redis;

	@Override
	public boolean add(int userId, String headImg) {
		String key = RedisKey.getUserHeadImg();
		this.redis.hset(key, String.valueOf(userId), headImg);
		return true;
	}

	@Override
	public String get(int userId) {
		String key = RedisKey.getUserHeadImg();
		return this.redis.hget(key, String.valueOf(userId));
	}

}
