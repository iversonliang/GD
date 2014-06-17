package com.GD.handler.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.GD.handler.VideoHandler;
import com.GD.model.User;
import com.GD.model.Video;
import com.GD.service.UserService;
import com.GD.type.StatusType;
import com.GD.type.VideoGradeType;
import com.GD.util.CheckUtil;
import com.GD.util.DateUtil;
import com.GD.util.UserUtil;
import com.GD.util.VideoUtil;
import com.GD.web.form.VideoForm;
import com.GD.web.vo.VideoVO;

@Component
public class VideoHandlerImpl implements VideoHandler {
	
	@Autowired
	private UserService userService;

	@Override
	public Video getVideo(int userId, VideoForm form) {
		Video video = new Video();
		video.setComments(0);
		video.setDel(false);
		video.setDescription(StringUtils.defaultIfEmpty(form.getDescription().trim(), ""));
		video.setLabel(StringUtils.defaultIfEmpty(form.getLabel().trim(), ""));
		video.setLove(0);
		video.setName(form.getName());
		video.setPlay(0);
		video.setPosttime(new Date());
		video.setStatus(StatusType.NORMAL.getKey());
		video.setUrl(form.getUrl().trim());
		video.setUserId(userId);
		video.setVideoGradeType(VideoGradeType.COMMON.getKey());
		video.setVideoSourceType(form.getSourceType());
		video.setVideoType(form.getType());
		video.setImgUrl("");
		
		CheckUtil.checkVideo(video);
		User user = userService.get(userId);
		UserUtil.checkNull(user);
		String nickname = user.getNickname();
		nickname = StringUtils.defaultIfEmpty(nickname, user.getUsername());
		video.setNickname(nickname);
		video = VideoUtil.getYoukuVideoInfo(video);
		return video;
	}

	@Override
	public List<VideoVO> toVoList(List<Video> list) {
		List<VideoVO> voList = new ArrayList<VideoVO>();
		for (Video video : list) {
			User user = userService.get(video.getUserId());
			VideoVO vo = new VideoVO();
			vo.setComments(video.getComments());
			vo.setDeployTimeTips(DateUtil.getDeployTimeTips(video.getPosttime()));
			vo.setDescription(video.getDescription());
			vo.setHeadImg(user.getHeadImg());
			vo.setImgUrl(video.getImgUrl());
			vo.setLabel(video.getLabel());
			vo.setLove(video.getLove());
			vo.setName(video.getName());
			vo.setNickname(video.getNickname());
			vo.setPlay(video.getPlay());
			vo.setPlayUrl(video.getPlayUrl());
			vo.setPosttime(video.getPosttime());
			vo.setUserId(video.getUserId());
			vo.setVideoGradeType(video.getVideoGradeType());
			vo.setVideoId(video.getVideoId());
			vo.setVideoSourceType(video.getVideoSourceType());
			vo.setVideoType(video.getVideoType());
			voList.add(vo);
		}
		return voList;
	}

}
