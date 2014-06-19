package com.GD.dao.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.ActiveUserDao;
import com.GD.model.User;
import com.GD.redis.Redis;
import com.GD.util.DateUtil;
import com.GD.util.Json;
import com.GD.util.RedisKey;

/**
 * 活跃用户
 * 
 * @author iversonLiang
 * 
 */
@Repository
public class ActiveUserDaoRedisImpl implements ActiveUserDao {

	@Autowired
	private Redis redis;

	@Override
	public boolean add(User user, Date date) {
		String key = RedisKey.getActiveUser();
		int score = DateUtil.getSecond(date);
		this.redis.zadd(key, score, Json.toJson(user));
		return true;
	}

	@Override
	public boolean remove(User user) {
		String key = RedisKey.getActiveUser();
		this.redis.zrem(key, Json.toJson(user));
		return true;
	}

	@Override
	public List<User> list(int start, int size) {
		String key = RedisKey.getActiveUser();
		Set<String> set = this.redis.zrevrange(key, start, start + size - 1);
		if (set == null) {
			return null;
		}
		List<String> jsonList = new ArrayList<String>(set);
		List<User> list = Json.toObject(jsonList, User.class);
		return list;
	}

	@Override
	public boolean update(User newUser, User oldUser) {
		String key = RedisKey.getActiveUser();
		long score = this.redis.zrank(key, Json.toJson(oldUser));
		this.redis.zrem(key, Json.toJson(oldUser));
		this.redis.zadd(key, score, Json.toJson(newUser));
		return true;
	}
	
}
