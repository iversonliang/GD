package com.GD.handler;

import java.util.List;
import java.util.Map;

import com.GD.model.Video;
import com.GD.web.form.VideoForm;
import com.GD.web.form.VideoSearchForm;
import com.GD.web.vo.VideoVO;

public interface VideoHandler {

	public Video getVideo(int userId, VideoForm form);
	
	public List<VideoVO> toVoList(List<Video> list);
	
	public Map<String, Object> getVideoSearchForm(VideoSearchForm form);
}
