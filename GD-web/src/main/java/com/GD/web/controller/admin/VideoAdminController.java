package com.GD.web.controller.admin;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.GD.interceptor.LoginRequired;
import com.GD.model.Video;
import com.GD.service.VideoService;
import com.GD.type.HomeType;
import com.GD.type.SortType;
import com.GD.util.Pager;
import com.GD.util.ViewUtil;

@Controller
@RequestMapping(value = VideoAdminController.DIR)
public class VideoAdminController {
	
	public static final String DIR = "/admin/video";
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private VideoService videoService;
	
	@LoginRequired
	@RequestMapping( value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session, Integer page, Integer homeType) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (homeType == null) {
			homeType = 0;
		}
		int count = videoService.countAll(HomeType.toType(homeType), true);
		Pager pager = new Pager(count, page, 10, "/admin/video/index.do", null);
		List<Video> list = videoService.listAll(HomeType.toType(homeType), SortType.ALL, true, pager.getFirst(), 10);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("videoList", list);
		model.addObject("pager", pager);
		model.addObject("type", "video");
		return model;
	}
	
	@LoginRequired
	@ResponseBody
	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response, HttpSession session, int videoId) {
		int userId = (Integer) session.getAttribute("userId");
		boolean result = videoService.del(userId, videoId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
	
	@LoginRequired
	@ResponseBody
	@RequestMapping(value="/unDelete.do", method=RequestMethod.GET)
	public Map<String, Object> unDelete(HttpServletRequest request, HttpServletResponse response, HttpSession session, int videoId) {
		int userId = (Integer) session.getAttribute("userId");
		boolean result = videoService.unDel(userId, videoId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}

	@LoginRequired
	@ResponseBody
	@RequestMapping(value="/setHomeType.do", method=RequestMethod.GET)
	public Map<String, Object> setHomeType(HttpServletRequest request, HttpServletResponse response, HttpSession session, int videoId, int homeType, Integer indexNum) {
		if (indexNum == null || indexNum < 0) {
			indexNum = 0;
		}
		int userId = (Integer) session.getAttribute("userId");
		boolean result = videoService.setHomeType(userId, videoId, HomeType.toType(homeType), indexNum);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
	
	@LoginRequired
	@ResponseBody
	@RequestMapping(value="/delHomeType.do", method=RequestMethod.GET)
	public Map<String, Object> delHomeType(HttpServletRequest request, HttpServletResponse response, HttpSession session, int videoId) {
		int userId = (Integer) session.getAttribute("userId");
		boolean result = videoService.delHomeType(userId, videoId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
}
