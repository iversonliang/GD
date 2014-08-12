package com.GD.dao.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.LikeVideoDao;
import com.GD.model.LikeVideo;
import com.GD.redis.Redis;
import com.GD.util.RedisKey;

@Repository
public class LikeVideoDaoRedisImpl implements LikeVideoDao{

	@Autowired
	private Redis redis;
	
	@Override
	public boolean add(LikeVideo likeVideo) {
		String key = RedisKey.getLikeVideo(likeVideo.getUserId());
		this.redis.sadd(key, String.valueOf(likeVideo.getVideoId()));
		return true;
	}

	@Override
	public List<LikeVideo> listByUser(int userId) {
		String key = RedisKey.getLikeVideo(userId);
		Set<String> set = this.redis.smembers(key);
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

}
