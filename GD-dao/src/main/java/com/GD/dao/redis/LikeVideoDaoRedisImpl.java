package com.GD.dao.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.LikeVideoDao;
import com.GD.model.LikeVideo;
import com.GD.redis.Redis;
import com.GD.util.DateUtil;
import com.GD.util.RedisKey;

@Repository
public class LikeVideoDaoRedisImpl implements LikeVideoDao{

	@Autowired
	private Redis redis;
	
	@Override
	public boolean add(LikeVideo likeVideo) {
		String key = RedisKey.getLikeVideo(likeVideo.getUserId());
		int seconds = DateUtil.getShortSeconds();
		this.redis.zadd(key, seconds, String.valueOf(likeVideo.getVideoId()));
		return true;
	}

	@Override
	public List<LikeVideo> listByUser(int userId, int start, int size) {
		String key = RedisKey.getLikeVideo(userId);
		Set<String> set = this.redis.zrevrange(key, start, start + size - 1);
		if (set == null || set.size() == 0) {
			return null;
		}
		List<LikeVideo> list = new ArrayList<LikeVideo>();
		for (String videoId : set) {
			LikeVideo likeVideo = new LikeVideo();
			likeVideo.setUserId(userId);
			likeVideo.setVideoId(Integer.valueOf(videoId));
			list.add(likeVideo);
		}
		return list;
	}

	@Override
	public int count(int userId) {
		String key = RedisKey.getLikeVideo(userId);
		Long result = this.redis.zcard(key);
		return (int) (result == null ? 0 : result);
	}

	@Override
	public boolean isLike(int userId, int videoId) {
		String key = RedisKey.getLikeVideo(userId);
		Double result = this.redis.zscore(key, String.valueOf(videoId));
		return result != null;
	}

	@Override
	public boolean delete(int userId, int videoId) {
		String key = RedisKey.getLikeVideo(userId);
		this.redis.zrem(key, String.valueOf(videoId));
		return true;
	}

}
