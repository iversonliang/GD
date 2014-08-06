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
import com.GD.model.InviteCode;
import com.GD.model.User;
import com.GD.service.InviteCodeService;
import com.GD.service.UserService;
import com.GD.type.RoleType;
import com.GD.util.Pager;
import com.GD.util.ViewUtil;

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
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("inviteCodeList", list);
		model.addObject("pager", pager);
		model.addObject("type", "inviteCode");
		return model;
	}
	
	@ResponseBody
	@LoginRequired
	@RequestMapping( value = "/create.do", method = RequestMethod.GET)
	public Map<String, Object> create(HttpServletRequest request, HttpServletResponse response, HttpSession session, int num) {
		int userId = (Integer) session.getAttribute("userId");
		User user = userService.get(userId);
		if (user == null || user.getRole() != RoleType.ADMIN.getKey()) {
			throw new RuntimeException("不是管理员");
		}
		inviteCodeService.create(num);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
}
