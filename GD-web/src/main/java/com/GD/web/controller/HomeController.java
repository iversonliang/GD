package com.GD.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
import com.GD.model.Video;
import com.GD.service.VideoService;
import com.GD.type.HomeType;
import com.GD.util.Pager;
import com.GD.util.ViewUtil;
import com.GD.web.vo.VideoVO;

@Controller
@RequestMapping(value = HomeController.DIR)
public class HomeController {

	public static final String DIR = "/";
	protected Log logger = LogFactory.getLog(this.getClass());

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
		int videoCount = videoService.countAll(HomeType.RECOMMEND, false);
		Pager pager = new Pager(videoCount, page, 16, "/index.do", null);
		List<Video> list = videoService.listAll(HomeType.RECOMMEND, pager.getFirst(), 16, false);
		List<VideoVO> voList = videoHandler.toVoList(list);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("isLogin", isLogin);
		model.addObject("pager", pager);
		model.addObject("videoVoList", voList);
		long end = System.currentTimeMillis();
		System.out.println("time:" + (end - start) + "ms");
		return model;
	}
}
