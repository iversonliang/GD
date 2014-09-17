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
import com.GD.model.Notice;
import com.GD.service.LastReadMessageService;
import com.GD.service.NoticeService;
import com.GD.type.MessageType;
import com.GD.util.Pager;
import com.GD.util.ViewUtil;
@Controller
@RequestMapping(value = NoticeController.DIR)
public class NoticeController {

	public static final String DIR = "/notice";
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private LastReadMessageService lastReadMessageService;
	@Autowired
	private NoticeService noticeService;
	
	@LoginRequired
	@RequestMapping( value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session, Integer page) {
		if (page == null || page < 1) {
			page = 1;
		}
		int userId = (Integer)session.getAttribute("userId");
		int count = noticeService.count(userId);
		lastReadMessageService.add(userId, count, MessageType.NOTICE);
		Pager pager = new Pager(count, page, 10, "/notice/index.do", null);
		List<Notice> list = noticeService.list(userId, pager.getFirst(), 10);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("noticeList", list);
		model.addObject("pager", pager);
		return model;
	}
}
