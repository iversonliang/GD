package com.GD.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.GD.handler.CommentHandler;
import com.GD.handler.VideoHandler;
import com.GD.interceptor.LoginRequired;
import com.GD.model.Comment;
import com.GD.model.User;
import com.GD.model.Video;
import com.GD.service.CommentService;
import com.GD.service.UserService;
import com.GD.service.VideoService;
import com.GD.type.ErrorTipsType;
import com.GD.util.ViewUtil;
import com.GD.web.form.VideoForm;
import com.GD.web.vo.CommentVO;

@Controller
@RequestMapping(value = VedioController.DIR)
public class VedioController {
	
	public static final String DIR = "/video";
	
	@Autowired
	private CommentHandler commentHandler;
	@Autowired
	private VideoHandler videoHandler;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private VideoService videoService;
	
	
	@LoginRequired
	@RequestMapping(value = "/contribute.do", method = RequestMethod.GET)
	public ModelAndView contribute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return ViewUtil.getView(DIR);
	}

	@LoginRequired
	@ResponseBody
	@RequestMapping(value = "/add.do", method = RequestMethod.POST)
	public Map<String, Object> add(HttpServletRequest request, HttpServletResponse response, HttpSession session, VideoForm form) {
		int userId = (Integer) session.getAttribute("userId");
		boolean result = false;
		Video video = null;
		String message = "";
		int errorCode = -1;
		try {
			video = videoHandler.getVideo(userId, form);
			result = videoService.add(video);
		} catch (Exception e) {
			e.printStackTrace();
			message = e.getMessage();
			errorCode = ErrorTipsType.toType(message).getKey();
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("message", message);
		map.put("errorCode", errorCode);
		return map;
	}
	
	@RequestMapping(value = "/video.do", method = RequestMethod.GET)
	public ModelAndView video(HttpServletRequest request, HttpServletResponse response, HttpSession session, int vid) {
		videoService.play(vid);
		Video video = videoService.get(vid);
		User user = userService.get(video.getUserId());
		List<Video> userVideoList = videoService.list(user.getUserId(), 0, 3);
		List<Comment> commentList = commentService.list(vid, 0, 10);
		List<CommentVO> commentVoList = commentHandler.toVoList(commentList);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("video", video);
		model.addObject("user", user);
		model.addObject("userVideoList", userVideoList);
		model.addObject("commentList", commentVoList);
		return model;
	}
	
}
