package com.GD.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.VideoDao;
import com.GD.model.User;
import com.GD.model.Video;
import com.GD.service.UserService;
import com.GD.service.VideoService;
import com.GD.type.RoleType;
import com.GD.type.StatusType;
import com.GD.type.VideoGradeType;
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
	public boolean add(int userId, String name, String description, String label, String url, VideoType videoType) {
		User user = userService.get(userId);
		UserUtil.checkNull(user);
		Video video = new Video();
		video.setComments(0);
		video.setDel(false);
		video.setDescription(description);
		video.setLabel(label);
		video.setLove(0);
		video.setName(name);
		video.setNickname(user.getNickname());
		video.setPlay(0);
		video.setPosttime(new Date());
		video.setStatus(StatusType.NORMAL.getKey());
		video.setUrl(url);
		video.setUserId(userId);
		video.setVideoGradeType(VideoGradeType.COMMON.getKey());
		video.setVideoType(videoType.getKey());
		return videoDao.add(video);
	}

	@Override
	public int count(StatusType statusType, VideoType videoType, VideoGradeType videoGradeType, String name, String label) {
		return videoDao.count(statusType.getKey(), videoType.getKey(), videoGradeType.getKey(), name, label);
	}

	@Override
	public List<Video> list(StatusType statusType, VideoType videoType, VideoGradeType videoGradeType, String name, String label, int start, int size) {
		return videoDao.list(statusType.getKey(), videoType.getKey(), videoGradeType.getKey(), name, label, start, size);
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
	public boolean addCommont(int videoId, String comment) {
		// TODO TO BE DONE
		return false;
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


}
