package com.GD.handler;

import com.GD.model.Video;
import com.GD.web.form.VideoForm;

public interface VideoHandler {

	public Video getVideo(int userId, VideoForm form);
}
