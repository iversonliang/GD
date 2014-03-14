package com.GD.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.TestRedisDao;
import com.GD.redis.Redis;

@Repository
public class TestRedisDaoRedisImpl implements TestRedisDao {
	
	@Autowired
	private Redis redis;

	@Override
	public String get(String key) {
		return redis.get(key);
	}

	@Override
	public boolean set(String key, String value) {
		redis.set(key, value);
		return true;
	}

}
