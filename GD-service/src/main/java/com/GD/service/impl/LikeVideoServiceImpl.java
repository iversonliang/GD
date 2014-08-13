package com.GD.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.LikeVideoDao;
import com.GD.model.LikeVideo;
import com.GD.service.LikeVideoService;
import com.GD.util.ListUtil;

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
		List<LikeVideo> list = likeVideoDao.listByUser(userId, 0, Integer.MAX_VALUE);
		boolean result = false;
		for (LikeVideo likeVideo : list) {
			if (likeVideo.getVideoId() == videoId) {
				result = true;
				break;
			}
		}
		return result;
	}

	@Override
	public int count(int userId) {
		return likeVideoDao.count(userId);
	}

	@Override
	public List<Integer> listLikeVideoId(int userId, int start, int size) {
		List<LikeVideo> list = likeVideoDao.listByUser(userId, start, size);
		list = ListUtil.defaultList(list);
		List<Integer> result = new ArrayList<Integer>();
		for (LikeVideo likeVideo : list) {
			result.add(likeVideo.getVideoId());
		}
		return result;
	}

}
