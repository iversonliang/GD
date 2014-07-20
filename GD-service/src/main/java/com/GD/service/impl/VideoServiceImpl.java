package com.GD.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.GD.dao.VideoDao;
import com.GD.model.User;
import com.GD.model.Video;
import com.GD.service.UserService;
import com.GD.service.VideoService;
import com.GD.type.ErrorTipsType;
import com.GD.type.HomeType;
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
	public int count(StatusType statusType, VideoType videoType, HomeType homeType, VideoGradeType videoGradeType, VideoSourceType videoSourceType, String name, String label, boolean showDel) {
		return videoDao.count(statusType.getKey(), videoType.getKey(), homeType.getKey(), videoGradeType.getKey(), videoSourceType.getKey(), showDel, name, label);
	}

	@Override
	public List<Video> list(StatusType statusType, VideoType videoType, HomeType homeType, VideoGradeType videoGradeType, VideoSourceType videoSourceType, boolean showDel, String name, String label,
			int start, int size) {
		return videoDao.list(statusType.getKey(), videoType.getKey(), homeType.getKey(), videoGradeType.getKey(), videoSourceType.getKey(), showDel, name, label, start, size);
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
		Video video = videoDao.get(videoId);
		VideoUtil.checkNull(video);
		this.checkDelAuthority(userId, video);
		boolean result = false;
		result = videoDao.del(videoId);
		if (result && video.getHomeType() != HomeType.IGNORE.getKey()) {
			videoDao.updateIndexBetween(video.getHomeType(), video.getIndexNum(), Integer.MAX_VALUE, false);
		}
		return result;
	}

	private void checkDelAuthority(int userId, Video video) {
		boolean allowDel = false;
		User user = userService.get(userId);
		UserUtil.checkNull(user);
		if (RoleType.toType(user.getRole()).getKey() != RoleType.USER.getKey()) {
			allowDel = true; // 管理员
		} else {
			VideoUtil.checkAuthority(userId, video);
			allowDel = true;
		}
		Assert.isTrue(allowDel, "用户[" + userId + "]没有权限删除视频[" + video.getVideoId() + "]");
	}
	
	@Override
	public int countAll(HomeType homeType, boolean showDel) {
		return this.count(StatusType.NORMAL, VideoType.ALL, homeType, VideoGradeType.ALL, VideoSourceType.ALL, null, null, showDel);
	}

	@Override
	public List<Video> listAll(HomeType homeType, int start, int size, boolean showDel) {
		return this.list(StatusType.NORMAL, VideoType.ALL, homeType, VideoGradeType.ALL, VideoSourceType.ALL, showDel, null, null, start, size);
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
		return videoDao.countByUser(userId);
	}

	@Override
	public void checkVideo(int videoId) {
		Video video = videoDao.get(videoId);
		if (video == null) {
			throw new RuntimeException(ErrorTipsType.VIDEO_ID_ERROR.getDesc());
		}
	}

	@Override
	public boolean setHomeType(int userId, int videoId, HomeType homeTyp, int indexNum) {
		User user = userService.get(userId);
		UserUtil.checkNull(user);
		UserUtil.checkAdminAuthority(user);
		Video video = videoDao.get(videoId);
		VideoUtil.checkNull(video);
		boolean result = false;
		if (video.getHomeType() == HomeType.IGNORE.getKey()) {
			result = this.addHomeType(video, homeTyp, indexNum);
		} else {
			result = this.updateHomeType(video, homeTyp, indexNum);
		}
		return result;
	}
	
	private boolean addHomeType(Video video, HomeType type, int indexNum) {
		int maxIndexNum = videoDao.getMaxIndexNum(type.getKey());
		if (indexNum > maxIndexNum + 1 || indexNum < 1) {
			indexNum = maxIndexNum + 1;
		}
		if (indexNum >=1 && indexNum <= maxIndexNum) {
			videoDao.updateIndexBetween(type.getKey(), indexNum, Integer.MAX_VALUE, true);
		}
		return videoDao.updateHomeTypeIndex(video.getVideoId(), type.getKey(), indexNum);
	}

	private boolean updateHomeType(Video video, HomeType type, int indexNum) {
		int oldIndexNum = video.getIndexNum();
		if (oldIndexNum != indexNum) {
			int maxIndexNum = videoDao.getMaxIndexNum(type.getKey());
			if (indexNum > maxIndexNum || indexNum < 1) {
				throw new RuntimeException("更新的顺序[" + indexNum + "]不在范围内[1, " + maxIndexNum + "]");
			}
			videoDao.updateHomeTypeIndex(video.getVideoId(), type.getKey(), Integer.MAX_VALUE);
			Video exchangeObj = videoDao.getByIndexNum(type.getKey(), indexNum);
			videoDao.updateHomeTypeIndex(exchangeObj.getVideoId(), type.getKey(), oldIndexNum);
		}
		return videoDao.updateHomeTypeIndex(video.getVideoId(), type.getKey(), indexNum);
	}

	@Override
	public boolean unDel(int userId, int videoId) {
		User user = userService.get(userId);
		UserUtil.checkNull(user);
		UserUtil.checkAdminAuthority(user);
		return videoDao.unDel(videoId);
	}
}
