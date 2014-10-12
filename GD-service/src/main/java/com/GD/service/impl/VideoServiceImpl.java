package com.GD.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.GD.dao.DefenseFlushDao;
import com.GD.dao.VideoDao;
import com.GD.model.Notice;
import com.GD.model.User;
import com.GD.model.Video;
import com.GD.service.CommentService;
import com.GD.service.LikeVideoService;
import com.GD.service.NoticeService;
import com.GD.service.UserService;
import com.GD.service.VideoService;
import com.GD.type.ErrorTipsType;
import com.GD.type.HomeType;
import com.GD.type.RoleType;
import com.GD.type.SortType;
import com.GD.type.StatusType;
import com.GD.type.TimeLimitType;
import com.GD.type.VideoGradeType;
import com.GD.type.VideoSourceType;
import com.GD.type.VideoType;
import com.GD.util.NoticeUtil;
import com.GD.util.UserUtil;
import com.GD.util.VideoUtil;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private CommentService commentService;
	@Autowired
	private LikeVideoService likeVideoService;
	@Autowired
	private DefenseFlushDao defenseFlushDao;
	@Autowired
	private VideoDao videoDao;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;

	@Override
	public boolean add(Video video) {
		boolean result = videoDao.add(video);
		if (result) {
			userService.updateActiveTime(video.getUserId());
			userService.incrVideo(video.getUserId());
		}
		return result;
	}

	@Override
	public int count(StatusType statusType, VideoType videoType, HomeType homeType, VideoGradeType videoGradeType, VideoSourceType videoSourceType, TimeLimitType timeLimitType, String keyword, boolean showDel) {
		return videoDao.count(statusType.getKey(), videoType.getKey(), homeType.getKey(), videoGradeType.getKey(), videoSourceType.getKey(), timeLimitType.getKey(), keyword, showDel);
	}

	@Override
	public List<Video> list(StatusType statusType, VideoType videoType, HomeType homeType, VideoGradeType videoGradeType, VideoSourceType videoSourceType, SortType sortType, TimeLimitType timeLimitType, boolean showDel,
			String keyword, int start, int size) {
		return videoDao.list(statusType.getKey(), videoType.getKey(), homeType.getKey(), videoGradeType.getKey(), videoSourceType.getKey(), sortType.getKey(), timeLimitType.getKey(), keyword, showDel, start, size);
	}

	@Override
	public boolean play(int videoId, String sessionId) {
		boolean isView = defenseFlushDao.isExist(sessionId, videoId);
		if (isView) {
			return true;
		} else {
			defenseFlushDao.add(sessionId, videoId);
		}
		return videoDao.play(videoId);
	}

	@Override
	public boolean love(int userId, int videoId) {
		boolean isLiked = likeVideoService.isLiked(userId, videoId);
		if (isLiked) {
			return false;
		}
		boolean result = videoDao.love(videoId);
		if (result) {
			likeVideoService.add(userId, videoId);
		}
		return result;
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
		commentService.delByVideo(videoId);
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
	public int countAll(HomeType homeType, TimeLimitType timeLimitType, boolean showDel) {
		return this.count(StatusType.NORMAL, VideoType.ALL, homeType, VideoGradeType.ALL, VideoSourceType.ALL, timeLimitType, null, showDel);
	}

	@Override
	public List<Video> listAll(HomeType homeType, SortType sortType, TimeLimitType timeLimitType, boolean showDel, int start, int size) {
		return this.list(StatusType.NORMAL, VideoType.ALL, homeType, VideoGradeType.ALL, VideoSourceType.ALL, sortType, timeLimitType, showDel, null, start, size);
	}

	@Override
	public Video get(int videoId) {
		return videoDao.get(videoId);
	}

	@Override
	public List<Video> list(int userId, VideoSourceType videoSourceType, int start, int size) {
		return videoDao.list(userId, videoSourceType.getKey(), start, size);
	}

	@Override
	public int countByUser(int userId, VideoSourceType videoSourceType) {
		return videoDao.countByUser(userId, videoSourceType.getKey());
	}

	@Override
	public void checkVideo(int videoId) {
		Video video = videoDao.get(videoId);
		if (video == null) {
			throw new RuntimeException(ErrorTipsType.VIDEO_ID_ERROR.getDesc());
		}
	}

	@Override
	public boolean setHomeType(int userId, int videoId, HomeType homeType, int indexNum) {
		Video video = videoDao.get(videoId);
		VideoUtil.checkNull(video);
		boolean result = false;
		if (video.getHomeType() == HomeType.IGNORE.getKey()) {
			Notice notice = new Notice();
			notice.setContent(NoticeUtil.getVideoNoticeContent(video, HomeType.RECOMMEND));
			notice.setImgUrl("/images/avatar_system.jpg");
			System.out.println("------------- 没有上传图片 ---------------");
			notice.setOpUserId(userId);
			notice.setPosttime(new Date());
			notice.setUserId(video.getUserId());
			notice.setVideoId(videoId);
			result = this.addHomeType(video, homeType, indexNum);
			noticeService.add(notice);
		} else {
			result = this.updateHomeType(video, homeType, indexNum);
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

	@Override
	public boolean delHomeType(int userId, int videoId) {
		Video video = videoDao.get(videoId);
		VideoUtil.checkNull(video);
		this.checkDelAuthority(userId, video);
		boolean result = false;
		result = videoDao.updateHomeTypeIndex(videoId, HomeType.IGNORE.getKey(), 0);
		if (result && video.getHomeType() != HomeType.IGNORE.getKey()) {
			videoDao.updateIndexBetween(video.getHomeType(), video.getIndexNum(), Integer.MAX_VALUE, false);
		}
		return result;
	}

	@Override
	public boolean updateGradeType(int userId, int videoId, VideoGradeType gradeType) {
		User user = userService.get(userId);
		UserUtil.checkNull(user);
		UserUtil.checkAdminAuthority(user);
		return videoDao.updateVideoGradeType(videoId, gradeType.getKey());
	}

	@Override
	public List<Video> listLike(int userId, int start, int size) {
		List<Integer> videoIdList = likeVideoService.listLikeVideoId(userId, start, size);
		List<Video> list = new ArrayList<Video>();
		for (Integer videoId : videoIdList) {
			Video video = this.get(videoId);
//			if (video == null || video.isDel()) {
//				likeVideoService.delete(userId, videoId);
//				continue;
//			}
			list.add(video);
		}
		return list;
	}

	@Override
	public Video checkAuthor(int userId, int videoId) {
		Video video = this.get(videoId);
		Assert.notNull(video, "视频为空[ " + videoId + " ]");
		Assert.isTrue(userId == video.getUserId(), "用户[ " + userId + " ]不是视频[ " + videoId + " ]作者");
		return video;
	}

	@Override
	public boolean update(Video video) {
		return videoDao.update(video);
	}

	@Override
	public void fixHomeType() {
		List<Video> list = videoDao.listAllHomeType();
		for (int i=1;i<=list.size();i++) {
			Video video = list.get(i - 1); 
			videoDao.updateHomeTypeIndex(video.getVideoId(), i);
		}
	}

	@Override
	public boolean updateNicknameByUser(int userId, String nickname) {
		return videoDao.updateNicknameByUser(userId, nickname);
	}
	
}
