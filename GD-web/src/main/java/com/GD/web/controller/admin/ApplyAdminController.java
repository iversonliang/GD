package com.GD.web.controller.admin;

import java.util.ArrayList;
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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.GD.interceptor.LoginRequired;
import com.GD.model.Apply;
import com.GD.model.User;
import com.GD.service.ApplyService;
import com.GD.service.UserService;
import com.GD.util.Pager;
import com.GD.util.ViewUtil;
import com.GD.web.vo.ApplyAdminVO;

@Controller
@RequestMapping(value = ApplyAdminController.DIR)
public class ApplyAdminController {

	public static final String DIR = "/admin/apply";
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private UserService userService;
	@Autowired
	private ApplyService applyService;
	
	@LoginRequired
	@RequestMapping( value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session, Integer page) {
		if (page == null || page < 1) {
			page = 1;
		}
		int count = applyService.count();
		Pager pager = new Pager(count, page, 10, "/admin/apply/index.do", null);
		List<Apply> list = applyService.list(pager.getFirst(), 10);
		List<ApplyAdminVO> voList = new ArrayList<ApplyAdminVO>();
		for (Apply apply : list) {
			ApplyAdminVO vo = new ApplyAdminVO();
			vo.setApplyId(apply.getApplyId());
			vo.setCrew(apply.getCrew());
			vo.setEmail(apply.getEmail());
			vo.setInviteCodeId(apply.getInviteCodeId());
			vo.setLocation(apply.getLocation());
			vo.setName(apply.getName());
			vo.setOupsUrl(apply.getOupsUrl());
			vo.setPass(apply.isPass());
			vo.setPosttime(apply.getPosttime());
			vo.setUserId(apply.getUserId());
			if (vo.getUserId() != 0) {
				User user = userService.get(vo.getUserId());
				Assert.notNull(user, "没有对应的用户[" + vo.getUserId() + "]");
				vo.setUsername(user.getUsername());
			}
			
			voList.add(vo);
		}
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("list", voList);
		model.addObject("pager", pager);
		model.addObject("type", "apply");
		return model;
	}
	
	@LoginRequired
	@RequestMapping( value = "/pass.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> pass(HttpServletRequest request, HttpServletResponse response, HttpSession session, int applyId) {
		boolean result = applyService.pass(applyId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
	
}
