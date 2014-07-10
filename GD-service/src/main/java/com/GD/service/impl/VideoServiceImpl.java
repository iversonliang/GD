package com.GD.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.VideoDao;
import com.GD.model.User;
import com.GD.model.Video;
import com.GD.service.UserService;
import com.GD.service.VideoService;
import com.GD.type.ErrorTipsType;
import com.GD.type.RoleType;
import com.GD.type.StatusType;
import com.GD.type.VideoGradeType;
import com.GD.type.VideoSourceType;
import com.GD.type.VideoType;
import com.GD.util.UserUtil;
import com.GD.util.VideoUtil;

@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private VideoDao videoDao;
	@Autowired
	private UserService userService;

	@Override
	public boolean add(Video video) {
		boolean result = videoDao.add(video);
		if (result) {
			userService.incrVideo(video.getUserId());
		}
		return result;
	}

	@Override
	public int count(StatusType statusType, VideoType videoType, VideoGradeType videoGradeType, VideoSourceType videoSourceType, String name, String label) {
		return videoDao.count(statusType.getKey(), videoType.getKey(), videoGradeType.getKey(), videoSourceType.getKey(), name, label);
	}

	@Override
	public List<Video> list(StatusType statusType, VideoType videoType, VideoGradeType videoGradeType, VideoSourceType videoSourceType, String name, String label, int start, int size) {
		return videoDao.list(statusType.getKey(), videoType.getKey(), videoGradeType.getKey(), videoSourceType.getKey(), name, label, start, size);
	}

	@Override
	public boolean play(int videoId) {
		return videoDao.play(videoId);
	}

	@Override
	public boolean love(int videoId) {
		return videoDao.love(videoId);
	}

	@Override
	public boolean incrComment(int videoId) {
		return videoDao.commont(videoId);
	}

	@Override
	public boolean del(int userId, int videoId) {
		User user = userService.get(userId);
		UserUtil.checkNull(user);
		boolean allowDel = false;
		if (RoleType.toType(user.getRole()).getKey() != RoleType.USER.getKey()) {
			allowDel = true; //管理员
		} else {
			Video video = videoDao.get(videoId); //检查用户权限
			VideoUtil.checkNull(video);
			VideoUtil.checkAuthority(userId, video);
			allowDel = true;
		}
		boolean result = false;
		if (allowDel) {
			result = videoDao.del(videoId);
		}
		return result;
	}

	@Override
	public int countAll() {
		return this.count(StatusType.NORMAL, VideoType.ALL, VideoGradeType.ALL, VideoSourceType.ALL, null, null);
	}

	@Override
	public List<Video> listAll(int start, int size) {
		return this.list(StatusType.NORMAL, VideoType.ALL, VideoGradeType.ALL, VideoSourceType.ALL, null, null, start, size);
	}

	@Override
	public Video get(int videoId) {
		return videoDao.get(videoId);
	}

	@Override
	public List<Video> list(int userId, int start, int size) {
		return videoDao.list(userId, start, size);
	}

	@Override
	public int count(int userId) {
		return videoDao.count(userId);
	}

	@Override
	public void checkVideo(int videoId) {
		Video video = videoDao.get(videoId);
		if (video == null) {
			throw new RuntimeException(ErrorTipsType.VIDEO_ID_ERROR.getDesc());
		}
	}


}
