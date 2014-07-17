package com.GD.web.controller;

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

import com.GD.interceptor.LoginRequired;
import com.GD.model.Announcement;
import com.GD.service.AnnouncementService;
import com.GD.util.Pager;
import com.GD.util.ViewUtil;
@Controller
@RequestMapping(value = AnnouncementController.DIR)
public class AnnouncementController {

	public static final String DIR = "/announcement";
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
		Pager pager = new Pager(count, page, 10, "/announcement/index.do", null);
		List<Announcement> list = announcementService.list(pager.getFirst(), 10);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("announcementList", list);
		model.addObject("pager", pager);
		return model;
	}
	
}
