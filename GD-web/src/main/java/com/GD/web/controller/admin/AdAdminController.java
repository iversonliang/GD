package com.GD.web.controller.admin;

import java.util.Date;
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
import com.GD.model.Ad;
import com.GD.model.User;
import com.GD.service.AdService;
import com.GD.service.UserService;
import com.GD.type.AdAreaType;
import com.GD.util.Pager;
import com.GD.util.UserUtil;
import com.GD.util.ViewUtil;
import com.GD.web.form.AdForm;

@Controller
@RequestMapping(value = AdAdminController.DIR)
public class AdAdminController {

	public static final String DIR = "/admin/ad";
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private UserService userService;
	@Autowired
	private AdService adService;
	
	@LoginRequired
	@RequestMapping( value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response, HttpSession session, Integer page, Integer adAreaType) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (adAreaType == null) {
			adAreaType = 0;
		}
		int count = adService.count(AdAreaType.toType(adAreaType));
		Pager pager = new Pager(count, page, 10, "/admin/video/index.do", null);
		List<Ad> list = adService.list(AdAreaType.toType(adAreaType), pager.getFirst(), 10);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("adList", list);
		model.addObject("pager", pager);
		model.addObject("type", "ad");
		model.addObject("adAreaList", AdAreaType.values());
		return model;
	}
	
	@LoginRequired
	@ResponseBody
	@RequestMapping(value="/add.do", method=RequestMethod.POST)
	public Map<String, Object> add(HttpServletRequest request, HttpServletResponse response, HttpSession session, AdForm form) {
		int userId = (Integer) session.getAttribute("userId");
		User user = userService.get(userId);
		UserUtil.checkNull(user);
		UserUtil.checkAdminAuthority(user);
		Ad ad = new Ad();
		ad.setAdAreaType(form.getAdAreaType());
		ad.setDel(false);
		ad.setImgUrl(form.getImgUrl());
		ad.setIndexNum(form.getIndexNum());
		ad.setPosttime(new Date());
		ad.setUrl(form.getUrl());
		boolean result = adService.add(userId, ad);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
	
	@LoginRequired
	@ResponseBody
	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public Map<String, Object> delete(HttpServletRequest request, HttpServletResponse response, HttpSession session, int adId) {
		int userId = (Integer) session.getAttribute("userId");
		User user = userService.get(userId);
		UserUtil.checkNull(user);
		UserUtil.checkAdminAuthority(user);
		boolean result = adService.delete(adId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
}
