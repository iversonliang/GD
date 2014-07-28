package com.GD.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.GD.handler.VideoHandler;
import com.GD.model.Ad;
import com.GD.model.Video;
import com.GD.service.AdService;
import com.GD.service.VideoService;
import com.GD.type.AdAreaType;
import com.GD.type.HomeType;
import com.GD.type.SortType;
import com.GD.type.StatusType;
import com.GD.type.TimeLimitType;
import com.GD.type.VideoGradeType;
import com.GD.type.VideoSourceType;
import com.GD.type.VideoType;
import com.GD.util.Pager;
import com.GD.util.ViewUtil;
import com.GD.web.form.VideoSearchForm;
import com.GD.web.vo.VideoVO;

@Controller
@RequestMapping(value = HomeController.DIR)
public class HomeController {

	public static final String DIR = "/";
	protected Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private AdService adService;
	@Autowired
	private VideoHandler videoHandler;
	@Autowired
	private VideoService videoService;
	
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session, Integer page) throws UnsupportedEncodingException {
		long start = System.currentTimeMillis();
		if (page == null || page < 1) {
			page = 1;
		}
		Integer userId = (Integer) session.getAttribute("userId");
		boolean isLogin = userId != null;
		int videoCount = videoService.countAll(HomeType.RECOMMEND, TimeLimitType.ALL, false);
		Pager pager = new Pager(videoCount, page, 16, "/index.do", null);
		List<Video> list = videoService.listAll(HomeType.RECOMMEND, SortType.ALL, TimeLimitType.ALL, false, pager.getFirst(), 16);
		List<VideoVO> voList = videoHandler.toVoList(list);
		
		List<Ad> adList = adService.list(AdAreaType.IGNORE, 0, Integer.MAX_VALUE);
		List<Ad> slideList = new ArrayList<Ad>();
		for (Ad ad : adList) {
			if (ad.getAdAreaType() == AdAreaType.SLIDE.getKey()) {
				slideList.add(ad);
			}
		}
		
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("isLogin", isLogin);
		model.addObject("pager", pager);
		model.addObject("videoVoList", voList);
		model.addObject("nav", "home");
		model.addObject("slideList", slideList);
		long end = System.currentTimeMillis();
		System.out.println("time:" + (end - start) + "ms");
		return model;
	}
	
	@RequestMapping(value = "opus.do", method = RequestMethod.GET)
	public ModelAndView opus(HttpServletRequest request, HttpServletResponse response, HttpSession session, VideoSearchForm form, Integer page) throws UnsupportedEncodingException {
		if (page == null || page < 1) {
			page = 1;
		}
		Integer userId = (Integer) session.getAttribute("userId");
		boolean isLogin = userId != null;
		
		VideoType videoType = form.getVideoType() == null ? VideoType.ALL : VideoType.toType(form.getVideoType());
		SortType sortType = form.getSortType() == null ? SortType.ALL : SortType.toType(form.getSortType());
		VideoGradeType videoGradeType = form.getVideoGradeType() == null ? VideoGradeType.ALL : VideoGradeType.toType(form.getVideoGradeType());
		TimeLimitType timeLimitType = form.getTimeLimitType() == null ? TimeLimitType.ALL : TimeLimitType.toType(form.getTimeLimitType());
		
		int videoCount = videoService.count(StatusType.NORMAL, videoType, HomeType.IGNORE, videoGradeType, VideoSourceType.ORIGINAL, timeLimitType, form.getKeyword(), false);
		Pager pager = new Pager(videoCount, page, 16, "/opus.do", null);
		List<Video> list = videoService.list(StatusType.NORMAL, videoType, HomeType.IGNORE, videoGradeType, VideoSourceType.ORIGINAL, sortType, timeLimitType, false, form.getKeyword(), pager.getFirst(), 16);
		List<VideoVO> voList = videoHandler.toVoList(list);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("isLogin", isLogin);
		model.addObject("pager", pager);
		model.addObject("videoVoList", voList);
		model.addObject("nav", "opus");
		model.addObject("videoTypeList", VideoType.values());
		model.addObject("videoGradeTypeList", VideoGradeType.values());
		model.addObject("sortTypeList", SortType.values());
		model.addObject("timeLimitTypeList", TimeLimitType.values());
		return model;
	}

	@RequestMapping(value = "inspiration.do", method = RequestMethod.GET)
	public ModelAndView inspiration(HttpServletRequest request, HttpServletResponse response, HttpSession session, VideoSearchForm form, Integer page) throws UnsupportedEncodingException {
		if (page == null || page < 1) {
			page = 1;
		}
		Integer userId = (Integer) session.getAttribute("userId");
		boolean isLogin = userId != null;
		Map<String, Object> params = videoHandler.getVideoSearchForm(form);
		
		VideoType videoType = form.getVideoType() == null ? VideoType.ALL : VideoType.toType(form.getVideoType());
		SortType sortType = form.getSortType() == null ? SortType.ALL : SortType.toType(form.getSortType());
		VideoGradeType videoGradeType = form.getVideoGradeType() == null ? VideoGradeType.ALL : VideoGradeType.toType(form.getVideoGradeType());
		TimeLimitType timeLimitType = form.getTimeLimitType() == null ? TimeLimitType.ALL : TimeLimitType.toType(form.getTimeLimitType());
		
		int videoCount = videoService.count(StatusType.NORMAL, videoType, HomeType.IGNORE, videoGradeType, VideoSourceType.REPRINT, timeLimitType, form.getKeyword(), false);
		Pager pager = new Pager(videoCount, page, 16, "/inspiration.do", params);
		List<Video> list = videoService.list(StatusType.NORMAL, videoType, HomeType.IGNORE, videoGradeType, VideoSourceType.REPRINT, sortType, timeLimitType, false, form.getKeyword(), pager.getFirst(), 16);
		List<VideoVO> voList = videoHandler.toVoList(list);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("isLogin", isLogin);
		model.addObject("pager", pager);
		model.addObject("videoVoList", voList);
		model.addObject("nav", "inspiration");
		model.addObject("videoTypeList", VideoType.values());
		model.addObject("videoGradeTypeList", VideoGradeType.values());
		model.addObject("sortTypeList", SortType.values());
		model.addObject("timeLimitTypeList", TimeLimitType.values());
		return model;
	}
}
