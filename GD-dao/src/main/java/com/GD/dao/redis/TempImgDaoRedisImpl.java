package com.GD.dao.redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.TempImgDao;
import com.GD.redis.Redis;
import com.GD.util.DateUtil;
import com.GD.util.RedisKey;

@Repository
public class TempImgDaoRedisImpl implements TempImgDao{
	
	@Autowired
	private Redis redis;

	@Override
	public boolean add(String imgType, String filename) {
		String key = RedisKey.getTempImg(imgType);
		Long result = this.redis.zadd(key, DateUtil.getShortSeconds(), filename);
		return result > 0;
	}

	@Override
	public List<String> listBefore(String imgType, Date date) {
		String key = RedisKey.getTempImg(imgType);
		Set<String> set = this.redis.zrangeByScore(key, DateUtil.getShortSeconds(), DateUtil.getShortSeconds(date));
		if (set == null) {
			return null; 
		}
		return new ArrayList<String>(set);
	}

}
