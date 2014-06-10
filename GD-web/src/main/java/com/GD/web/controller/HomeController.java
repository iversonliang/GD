package com.GD.web.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.GD.util.ViewUtil;

@Controller
@RequestMapping(value = HomeController.DIR)
public class HomeController {

	public static final String DIR = "/";
	protected Log logger = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpSession session) throws UnsupportedEncodingException {
		Integer userId = (Integer) session.getAttribute("userId");
		boolean isLogin = userId != null;
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("isLogin", isLogin);
		return model;
	}
}
