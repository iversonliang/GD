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
import com.GD.model.InviteCode;
import com.GD.model.User;
import com.GD.service.InviteCodeService;
import com.GD.service.UserService;
import com.GD.util.Pager;
import com.GD.util.ViewUtil;
import com.GD.web.vo.InviteCodeAdminVO;

@Controller
@RequestMapping(value = InviteCodeAdminController.DIR)
public class InviteCodeAdminController {

	public static final String DIR = "/admin/inviteCode";
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private UserService userService;
	@Autowired
	private InviteCodeService inviteCodeService;
	
	@LoginRequired
	@RequestMapping( value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session, Integer page) {
		if (page == null || page < 1) {
			page = 1;
		}
		int count = inviteCodeService.count();
		Pager pager = new Pager(count, page, 10, "/admin/inviteCode/index.do", null);
		List<InviteCode> list = inviteCodeService.list(pager.getFirst(), 10);
		List<InviteCodeAdminVO> voList = new ArrayList<InviteCodeAdminVO>();
		for (InviteCode inviteCode : list) {
			InviteCodeAdminVO vo = new InviteCodeAdminVO();
			vo.setInviteCodeId(inviteCode.getInviteCodeId());
			vo.setPosttime(inviteCode.getPosttime());
			vo.setStatus(inviteCode.getStatus());
			vo.setUseTime(inviteCode.getUseTime());
			vo.setUseUserId(inviteCode.getUseUserId());
			if (vo.getUseUserId() != 0) {
				User user = userService.get(vo.getUseUserId());
				Assert.notNull(user, "没有对应的用户[ " + vo.getUseUserId() + " ]");
				vo.setUseUsername(user.getUsername());
			}
			voList.add(vo);
		}
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("inviteCodeList", voList);
		model.addObject("pager", pager);
		model.addObject("type", "inviteCode");
		return model;
	}
	
	@ResponseBody
	@LoginRequired
	@RequestMapping( value = "/create.do", method = RequestMethod.GET)
	public Map<String, Object> create(HttpServletRequest request, HttpServletResponse response, HttpSession session, int num) {
		int userId = (Integer) session.getAttribute("userId");
		userService.checkAdmin(userId);
		inviteCodeService.create(num);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@ResponseBody
	@LoginRequired
	@RequestMapping( value = "/send.do", method = RequestMethod.GET)
	public Map<String, Object> send(HttpServletRequest request, HttpServletResponse response, HttpSession session, String inviteCodeId) {
		int userId = (Integer) session.getAttribute("userId");
		userService.checkAdmin(userId);
		boolean result = inviteCodeService.send(inviteCodeId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
}
