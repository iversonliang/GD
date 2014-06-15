package com.GD.handler.impl;

import java.util.Date;

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
import com.GD.util.UserUtil;
import com.GD.web.form.VideoForm;

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
		
		CheckUtil.checkVideo(video);
		User user = userService.get(userId);
		UserUtil.checkNull(user);
		String nickname = user.getNickname();
		nickname = StringUtils.defaultIfEmpty(nickname, user.getUsername());
		video.setNickname(nickname);
		return video;
	}

}
