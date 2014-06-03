package com.GD.util;

import com.GD.model.Video;

public class VideoUtil {

	public static void checkNull(Video video) {
		if (video == null) {
			throw new RuntimeException("视频为空");
		}
	}
	
	public static void checkAuthority(int userId, Video video) {
		if (userId != video.getUserId()) {
			throw new RuntimeException("用户[" + userId + "]没有权限操作视频[" + video.getVideoId() + "]");
		}
	}
}
