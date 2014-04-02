package com.GD.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.GD.email.MailInfo;
import com.GD.email.MailSender;
import com.GD.handler.UserHandler;
import com.GD.interceptor.LoginRequired;
import com.GD.model.User;
import com.GD.service.TestService;
import com.GD.service.UserService;
import com.GD.util.AuthCodeUtil;
import com.GD.util.CodeUtil;
import com.GD.util.EmailUtil;
import com.GD.web.form.UserForm;

@Controller
public class UserController {
	
	@Autowired
	private UserHandler userHandler;
	@Autowired
	private UserService userService;
	@Autowired
	private TestService testService;
	
	final private String format = "image/png";
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");
		System.out.println("index");
		return view;
	}

	@RequestMapping(value = "/getCode", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		HttpHeaders responseHeaders = new HttpHeaders();
		MediaType mtype = MediaType.valueOf(format);
		responseHeaders.setContentType(mtype);

		String code = AuthCodeUtil.generateIdentifyingCode(4);
		session.setAttribute("actualCode", code);
		BufferedImage tileImg = AuthCodeUtil.drawIdentifyingCode(code);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(tileImg, mtype.getSubtype(), out);
		byte[] tileBytes = out.toByteArray();

		System.out.println("getCodeId:" + request.getParameter("codeId"));
		IOUtils.closeQuietly(out);
		// ��ֹͼ�񻺴档  
		response.setHeader("Pragma", "no-cache"); 
		response.setHeader("Cache-Control", "no-cache"); 
		response.setDateHeader("Expires", 0); 
 
		response.setContentType("image/jpeg"); 
		return new ResponseEntity<byte[]>(tileBytes, responseHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public @ModelAttribute
	Object validate(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String code = request.getParameter("code");
		System.out.println("code:" + code);
		Object actualCode = session.getAttribute("actualCode");
		boolean result = false;
		if (code != null && actualCode != null && code.equalsIgnoreCase(actualCode.toString())) {
			result = true;
		}
		System.out.println("actualCode:" + actualCode.toString());
		session.setAttribute("actualCode", null);
		System.out.println("result:" + result);
		return result;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(UserForm form) throws UnsupportedEncodingException {
		User user = userHandler.form2User(form);
		System.out.println(user.getUsername() + " " + user.getPassword());
		boolean result = userService.add(user);
		ModelAndView model = new ModelAndView("login");
		if (result) {
			model.setViewName("success");
		}
		return model;
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.GET) 
	public ModelAndView activate(String code) {
		boolean result = userService.activate(code);
		System.out.println("���" + result);
		return null;
	}
	
	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public ModelAndView registerPage() {
		ModelAndView model = new ModelAndView("registerPage");
		return model;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		User user = userService.get(username, password);
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		System.out.println("login username:" + username + "��" + password);
		User user = new User();
		ModelAndView model = new ModelAndView("login");
		if (user != null) {
			session.setAttribute("username", username);
			System.out.println("login ---- sessionId:" + session.getId() + " username:" + session.getAttribute("username"));
			session.setMaxInactiveInterval(10);
			String url = (String) session.getAttribute("jumpPage");
			session.removeAttribute("jumpPage");
			System.out.println("jump url:" + url);
			if (StringUtils.isNotEmpty(url)) {
				model.setViewName("redirect:" + url);
			} else {
				model.setViewName("success");
			}
		}
		return model;
	}
	
	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView model = new ModelAndView("loginPage");
		return model;
	}
	
	@LoginRequired
	@RequestMapping(value = "/testLogin")
	public ModelAndView testLogin(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("------testLogin---------- " + request.getSession().getAttribute("test") + " " + request.getParameter("test"));
		ModelAndView model = new ModelAndView("success");
//		if (StringUtils.isEmpty(result) || !result.equals("logined")) {
//			model.setViewName("loginPage");
//		}
		return model;
	}
	
	@RequestMapping(value = "/testLoginPage", method = RequestMethod.GET)
	public ModelAndView testLoginPage() {
		ModelAndView model = new ModelAndView("testLoginPage");
		return model;
	}
	
	@RequestMapping(value = "test")
	@LoginRequired
	public ModelAndView test() {
		System.out.println("------------test--------------");
		System.out.println("testService:" + testService);
		testService.test();
		ModelAndView view = new ModelAndView("index");
		return view;
	}
	
}