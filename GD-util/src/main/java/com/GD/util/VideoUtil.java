package com.GD.util;

import org.apache.commons.lang.StringUtils;

import com.GD.model.CaptureVideo;
import com.GD.model.Video;
import com.GD.type.SourceSiteType;

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
	
	public static Video getYoukuVideoInfo(Video video) {
		try {
			CaptureVideo captureVideo = CaptureVideoUtil.getYouKuCaptureVideo(video.getUrl());
			video.setImgUrl(captureVideo.getThumbnail());
			video.setSourceSiteType(SourceSiteType.YOUKU.getKey());
			if (StringUtils.isEmpty(video.getName())) {
				video.setName(captureVideo.getTitle());
			}
			video.setPlayUrl(captureVideo.getFlashUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return video;
	}
}
