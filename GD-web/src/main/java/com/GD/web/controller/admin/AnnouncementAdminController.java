package com.GD.web.controller.admin;

import java.io.IOException;
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
import com.GD.model.Announcement;
import com.GD.service.AnnouncementService;
import com.GD.util.Pager;
import com.GD.util.ViewUtil;

@Controller
@RequestMapping(value = AnnouncementAdminController.DIR)
public class AnnouncementAdminController {

	public static final String DIR = "/admin/announcement";
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private AnnouncementService announcementService;
	
	@LoginRequired
	@RequestMapping( value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session, Integer page) {
		if (page == null || page < 1) {
			page = 1;
		}
		int count = announcementService.count();
		Pager pager = new Pager(count, page, 10, "/admin/announcement/index.do", null);
		List<Announcement> list = announcementService.list(pager.getFirst(), 10);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("announcementList", list);
		model.addObject("pager", pager);
		model.addObject("type", "announcement");
		return model;
	}
	
	@LoginRequired
	@ResponseBody
	@RequestMapping(value="/add.do", method=RequestMethod.GET)
	public void add(HttpServletRequest request, HttpServletResponse response, HttpSession session, String title, String content, String imgUrl) throws IOException {
		imgUrl = "/images/avatar_system.jpg";
		System.out.println("---------------这里没真的上传-------------");
		announcementService.add(title, content, imgUrl);
		response.sendRedirect("/admin/announcement/index.do");
	}
	
	@LoginRequired
	@ResponseBody
	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response, HttpSession session, int announcementId) {
		boolean result = announcementService.delete(announcementId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
	
	@LoginRequired
	@RequestMapping( value = "/addPage.do", method = RequestMethod.GET)
	public ModelAndView addPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		ModelAndView model = ViewUtil.getView(DIR);
		return model;
	}
	
}
