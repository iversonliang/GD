package com.GD.web.controller;

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
import com.GD.model.Apply;
import com.GD.service.ApplyService;
import com.GD.service.InviteCodeService;
import com.GD.util.CheckUtil;
import com.GD.util.ViewUtil;
import com.GD.web.form.ApplyForm;

@Controller
@RequestMapping(value = InviteCodeController.DIR)
public class InviteCodeController {
	
	public static final String DIR = "/inviteCode";
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private InviteCodeService inviteCodeService;
	@Autowired
	private ApplyService applyService;
	
	@LoginRequired
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return ViewUtil.getView(DIR);
	}
	
	@LoginRequired
	@RequestMapping(value = "/apply.do", method = RequestMethod.GET)
	public ModelAndView apply(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return ViewUtil.getView(DIR);
	}

	@LoginRequired
	@RequestMapping(value = "/result.do", method = RequestMethod.GET)
	public ModelAndView result(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return ViewUtil.getView(DIR);
	}

	@LoginRequired
	@RequestMapping(value = "/activate.do", method = RequestMethod.GET)
	public String activate(HttpServletRequest request, HttpServletResponse response, HttpSession session, String inviteCode) {
		int userId = (Integer)session.getAttribute("userId");
		inviteCodeService.activateUser(userId, inviteCode);
		return "redirect:/video/contribute.do";
	}
	
	@LoginRequired
	@RequestMapping(value = "/add.do", method = RequestMethod.GET)
	public String add(HttpServletRequest request, HttpServletResponse response, HttpSession session, ApplyForm form) {
		int userId = (Integer)session.getAttribute("userId");
		Apply apply = form.toObj(userId);
		CheckUtil.checkApply(apply);
		applyService.add(apply);
		return "redirect:/inviteCode/result.do";
	}

}
