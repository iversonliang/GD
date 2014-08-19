package com.GD.web.controller;

import java.util.Date;
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
import com.GD.model.Notice;
import com.GD.model.User;
import com.GD.model.Video;
import com.GD.service.ApplyService;
import com.GD.service.CommentService;
import com.GD.service.LikeVideoService;
import com.GD.service.NoticeService;
import com.GD.service.UserService;
import com.GD.service.VideoService;
import com.GD.type.ErrorTipsType;
import com.GD.type.HomeType;
import com.GD.type.SortType;
import com.GD.type.StatusType;
import com.GD.type.TimeLimitType;
import com.GD.type.VideoGradeType;
import com.GD.type.VideoSourceType;
import com.GD.type.VideoType;
import com.GD.util.Constants;
import com.GD.util.Pager;
import com.GD.util.ViewUtil;
import com.GD.web.form.VideoForm;
import com.GD.web.form.VideoSearchForm;
import com.GD.web.vo.CommentVO;
import com.GD.web.vo.VideoVO;

@Controller
@RequestMapping(value = VideoController.DIR)
public class VideoController {
	
	public static final String DIR = "/video";
	
	@Autowired
	private ApplyService applyService;
	@Autowired
	private CommentHandler commentHandler;
	@Autowired
	private VideoHandler videoHandler;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private LikeVideoService likeVideoService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private VideoService videoService;
	
	
	@LoginRequired
	@RequestMapping(value = "/contribute.do", method = RequestMethod.GET)
	public ModelAndView contribute(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int userId = (Integer) session.getAttribute("userId");
		boolean isActivate = applyService.isActivate(userId);
		ModelAndView model = ViewUtil.getView(DIR);
		if (!isActivate) {
			model = new ModelAndView("redirect:/inviteCode/index.do");
		}
		return model;
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
		videoService.play(vid, session.getId());
		Video video = videoService.get(vid);
		User user = userService.get(video.getUserId());
		List<Video> userVideoList = videoService.list(user.getUserId(), VideoSourceType.ALL, 0, 3);
		List<Comment> commentList = commentService.list(vid, 0, 10);
		List<CommentVO> commentVoList = commentHandler.toVoList(commentList);
		boolean isLiked = false;
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId != null) {
			isLiked = likeVideoService.isLiked(userId, vid);
		}
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("video", video);
		model.addObject("user", user);
		model.addObject("userVideoList", userVideoList);
		model.addObject("commentList", commentVoList);
		model.addObject("isLiked", isLiked);
		return model;
	}
	
	@RequestMapping(value = "/search.do", method = RequestMethod.GET)
	public ModelAndView search(HttpServletRequest request, HttpServletResponse response, HttpSession session, VideoSearchForm form, Integer page) {
		if (page == null || page < 1) {
			page = 1;
		}
		Integer userId = (Integer) session.getAttribute("userId");
		boolean isLogin = userId != null;
		
		VideoType videoType = form.getVideoType() == null ? VideoType.ALL : VideoType.toType(form.getVideoType());
		SortType sortType = form.getSortType() == null ? SortType.ALL : SortType.toType(form.getSortType());
		VideoGradeType videoGradeType = form.getVideoGradeType() == null ? VideoGradeType.ALL : VideoGradeType.toType(form.getVideoGradeType());
		TimeLimitType timeLimitType = form.getTimeLimitType() == null ? TimeLimitType.ALL : TimeLimitType.toType(form.getTimeLimitType());
		
		int count = videoService.count(StatusType.NORMAL, videoType, HomeType.IGNORE, videoGradeType, VideoSourceType.ALL, timeLimitType, form.getKeyword(), false);
		Pager pager = new Pager(count, page, 16, "/video/search.do", null);
		List<Video> list = videoService.list(StatusType.NORMAL, videoType, HomeType.IGNORE, videoGradeType, VideoSourceType.ALL, sortType, timeLimitType, false, form.getKeyword(), pager.getFirst(), 16);
		List<VideoVO> voList = videoHandler.toVoList(list);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("isLogin", isLogin);
		model.addObject("pager", pager);
		model.addObject("videoVoList", voList);
		model.addObject("nav", "search");
		model.addObject("videoTypeList", VideoType.values());
		model.addObject("videoGradeTypeList", VideoGradeType.values());
		model.addObject("sortTypeList", SortType.values());
		model.addObject("timeLimitTypeList", TimeLimitType.values());
		model.addObject("keyword", form.getKeyword());
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value = "/love.do", method = RequestMethod.GET)
	public Map<String, Object> love(HttpServletRequest request, HttpServletResponse response, HttpSession session, int vid) {
		boolean isLogin = false;
		boolean result = false;
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId != null) {
			isLogin = true;
			videoService.checkVideo(vid);
			result = videoService.love(userId, vid);
			Video video = videoService.get(vid);
			String username = (String) session.getAttribute("username");
			Notice notice = new Notice();
			notice.setImgUrl(Constants.SYS_DEFAULT_IMG);
			notice.setPosttime(new Date());
			String content = "你的视频《<a target=\"_blank\" href=\"/video/video.do?vid=" + vid + "\">" + video.getName() + "</a>》被" + username + "点赞喔";
			notice.setContent(content);
			notice.setUserId(userId);
			noticeService.add(notice);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("isLogin", isLogin);
		return map;
	}
	
	@RequestMapping(value = "/personal.do", method = RequestMethod.GET)
	public ModelAndView personal(HttpServletRequest request, HttpServletResponse response, HttpSession session, int userId, Integer page, Integer type) {
		Object idObj = session.getAttribute("userId");
		if (page == null || page < 1) {
			page = 1;
		}
		if (type == null || type < 1) {
			type = 1;
		}
		int myId = 0;
		if (idObj != null) {
			myId = (Integer) idObj;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("type", type);
		
		int totalCount;
		List<Video> list;
		Pager pager;
		if (type == 3) {
			totalCount = likeVideoService.count(userId);
			pager = new Pager(totalCount, page, 16, "/video/personal.do", params);
			list = videoService.listLike(userId,  pager.getFirst(), 16);
		} else {
			VideoSourceType videoSourceType = VideoSourceType.toType(type);
			totalCount  = videoService.countByUser(userId, videoSourceType);
			pager = new Pager(totalCount, page, 16, "/video/personal.do", params);
			list = videoService.list(userId, videoSourceType, pager.getFirst(), 16);
		}
		
		boolean isMyPage = myId == userId;
		User user = userService.get(userId);
		List<VideoVO> videoVoList = videoHandler.toVoList(list);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("user", user);
		model.addObject("isMyPage", isMyPage);
		model.addObject("videoVoList", videoVoList);
		model.addObject("pager", pager);
		model.addObject("type", type);
		return model;
	}
	
	@LoginRequired
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, HttpSession session, int vid, Integer type) {
		if (type == null) {
			type = 1;
		}
		if (type < 1 || type > 3) {
			throw new IllegalArgumentException("非法type[" + type + "]");
		}
		int userId = (Integer) session.getAttribute("userId");
		videoService.checkAuthor(userId, vid);
		if (type < 3) {
			videoService.del(userId, vid);
		}
		likeVideoService.delete(userId, vid);
		return "redirect:/video/personal.do?vid=" + vid + "&type=" + type + "&userId=" + userId;
	}
	
}
