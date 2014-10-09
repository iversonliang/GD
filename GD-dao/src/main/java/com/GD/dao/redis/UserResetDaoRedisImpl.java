package com.GD.dao.redis;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.UserResetDao;
import com.GD.redis.Redis;
import com.GD.util.RedisKey;

@Repository
public class UserResetDaoRedisImpl implements UserResetDao {

	@Autowired
	private Redis redis;

	private int SECONDS = 2 * 60 * 60;

	@Override
	public boolean add(int userId, String code) {
		String key = RedisKey.getUserReset(code);
		this.redis.set(key, String.valueOf(userId));
		this.redis.expire(key, SECONDS);
		return true;
	}

	@Override
	public int get(String code) {
		String key = RedisKey.getUserReset(code);
		String result = this.redis.get(key);
		if (StringUtils.isNotEmpty(result)) {
			return Integer.valueOf(result);
		}
		return 0;
	}

	@Override
	public boolean del(String code) {
		String key = RedisKey.getUserReset(code);
		this.redis.del(key);
		return true;
	}
}
