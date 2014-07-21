package com.GD.util;

import com.GD.model.Video;
import com.GD.type.NoticeType;
import com.GD.type.VideoSourceType;

public class NoticeUtil {

	public static String getVideoNoticeContent(Video video, NoticeType noticeType) {
		String content = "你发布的";
		if (video.getVideoSourceType() == VideoSourceType.ORIGINAL.getKey()) {
			content += "原创作品";
		} else if (video.getVideoSourceType() == VideoSourceType.REPRINT.getKey()) {
			content += "分享";
		} else {
			throw new RuntimeException("视频[" + video.getVideoId() + "]来源类型出错");
		}
		content += "：《<a target=\"_blank\" href=\"/video/video.do?vid=" + video.getVideoId() + "\">" + video.getName() + "</a>》 被管理员设置为 " + noticeType.getDesc();
		return content;
	}
}
