package com.GD.web.cache;

import org.apache.commons.collections.map.LRUMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.GD.model.Video;
import com.GD.service.UserService;
import com.GD.service.VideoService;

@Component
public class LruCache {
	
	@Autowired
	private VideoService videoService;
	@Autowired
	private UserService userService;
	
	public static LRUMap HEAD_IMG_MAP = new LRUMap(10000);
	public static LRUMap VIDEO_MAP = new LRUMap(1000);
	
	public String getHeadImg(int userId) {
		String headImg = (String) HEAD_IMG_MAP.get(userId);
		if (StringUtils.isEmpty(headImg)) {
			headImg = userService.getHeadImg(userId);
			HEAD_IMG_MAP.put(userId, headImg);
		}
		return headImg;
	}
	
	public Video getVideo(int videoId) {
		Video video = (Video) VIDEO_MAP.get(videoId);
		if (StringUtils.isEmpty(video)) {
			video = videoService.get(videoId);
			VIDEO_MAP.put(videoId, video);
		}
		return video;
	}

}
