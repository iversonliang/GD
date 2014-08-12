package com.GD.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.LikeVideoDao;
import com.GD.model.LikeVideo;
import com.GD.service.LikeVideoService;

@Service
public class LikeVideoServiceImpl implements LikeVideoService {

	@Autowired
	private LikeVideoDao likeVideoDao;
	
	@Override
	public boolean add(int userId, int videoId) {
		LikeVideo likeVideo = new LikeVideo();
		likeVideo.setUserId(userId);
		likeVideo.setVideoId(videoId);
		return likeVideoDao.add(likeVideo);
	}

	@Override
	public boolean isLiked(int userId, int videoId) {
		List<LikeVideo> list = likeVideoDao.listByUser(userId);
		boolean result = false;
		for (LikeVideo likeVideo : list) {
			if (likeVideo.getVideoId() == videoId) {
				result = true;
				break;
			}
		}
		return result;
	}

}
