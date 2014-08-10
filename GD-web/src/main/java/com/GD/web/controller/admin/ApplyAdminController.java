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
import com.GD.model.Apply;
import com.GD.service.ApplyService;
import com.GD.util.Pager;
import com.GD.util.ViewUtil;

@Controller
@RequestMapping(value = ApplyAdminController.DIR)
public class ApplyAdminController {

	public static final String DIR = "/admin/apply";
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ApplyService applyService;
	
	@LoginRequired
	@RequestMapping( value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session, Integer page) {
		if (page == null || page < 1) {
			page = 1;
		}
		int count = applyService.count();
		Pager pager = new Pager(count, page, 10, "/admin/inviteCode/index.do", null);
		List<Apply> list = applyService.list(pager.getFirst(), 10);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("list", list);
		model.addObject("pager", pager);
		model.addObject("type", "apply");
		return model;
	}
	
	@LoginRequired
	@RequestMapping( value = "/pass.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> pass(HttpServletRequest request, HttpServletResponse response, HttpSession session, int applyId) {
		int userId = (Integer)session.getAttribute("userId");
		boolean result = applyService.pass(userId, applyId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
	
}
