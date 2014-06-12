package com.GD.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.GD.handler.UserHandler;
import com.GD.interceptor.LoginRequired;
import com.GD.model.User;
import com.GD.service.UserService;
import com.GD.type.ErrorTipsType;
import com.GD.util.AuthCodeUtil;
import com.GD.util.CheckUtil;
import com.GD.util.FileUtil;
import com.GD.util.RequestUtil;
import com.GD.util.ViewUtil;
import com.GD.web.form.LoginForm;
import com.GD.web.form.UserForm;
import com.GD.web.servlet.BusinessHandleThread;

@Controller
@RequestMapping(value = UserController.DIR)
public class UserController {

	@Autowired
	private UserHandler userHandler;
	@Autowired
	private UserService userService;

	public static final String DIR = "/user";

	final private String format = "image/png";
	
	private static final String ACTUAL_CODE = "actualCode";
	private static final String JUMP_PAGE = "jumpPage";

	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCode.do", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getCode(HttpServletResponse response, HttpSession session) throws IOException {
		HttpHeaders responseHeaders = new HttpHeaders();
		MediaType mtype = MediaType.valueOf(format);
		responseHeaders.setContentType(mtype);

		String code = AuthCodeUtil.generateIdentifyingCode(4);
		session.setAttribute("actualCode", code);
		BufferedImage tileImg = AuthCodeUtil.drawIdentifyingCode(code);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(tileImg, mtype.getSubtype(), out);
		byte[] tileBytes = out.toByteArray();

		IOUtils.closeQuietly(out);
		// 禁止图像缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("image/jpeg");
		return new ResponseEntity<byte[]>(tileBytes, responseHeaders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> register(HttpSession session, UserForm form) throws UnsupportedEncodingException {
		String actualCode = (String) session.getAttribute(ACTUAL_CODE);
		String message = "";
		int errorCode = -1;
		boolean result = false;
		try {
			CheckUtil.checkCode(form.getCode(), actualCode);
			User user = userHandler.form2User(form);
			CheckUtil.checkRegisterUser(user);
			try {
				userService.add(user);
				user = userService.get(user.getUsername());
				session.setAttribute("username", user.getUsername());
				session.setAttribute("userId", user.getUserId());
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
				if (e.getMessage().contains("uniq_email")) {
					message = ErrorTipsType.EMAIL_IS_REGISTERED.getDesc();
					errorCode = ErrorTipsType.EMAIL_IS_REGISTERED.getKey();
				} else if (e.getMessage().contains("uniq_username")) {
					message = ErrorTipsType.USER_IS_REGISTERED.getDesc();
					errorCode = ErrorTipsType.USER_IS_REGISTERED.getKey();
				} else {
					message = ErrorTipsType.LOGIN_FAIL.getDesc();
					errorCode = ErrorTipsType.LOGIN_FAIL.getKey();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = e.getMessage();
			errorCode = ErrorTipsType.toType(message).getKey();
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("message", message);
		map.put("errorCode", errorCode);
		return map;
	}
	
	@LoginRequired
	@RequestMapping(value = "/userInfo.do", method = RequestMethod.GET)
	public ModelAndView userInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		User user = userService.get(userId);
		ModelAndView model = ViewUtil.getView(DIR);
		model.addObject("user", user);
		return model;
	}
	
	@LoginRequired
	@RequestMapping(value = "/updatePersonalImg.do", method = RequestMethod.GET)
	public ModelAndView updatePersonalImg(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return ViewUtil.getView(DIR);
	}
	
	@LoginRequired
	@RequestMapping(value = "/updatePassword.do", method = RequestMethod.GET)
	public ModelAndView updatePassword(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return ViewUtil.getView(DIR);
	}
	
	@RequestMapping(value = "/checkUsername.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkUsername(String username, String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isValid = userService.checkUsername(username);
		map.put("message", isValid ? "" : ErrorTipsType.USER_IS_REGISTERED.getDesc());
		map.put("errorCode", isValid ? -1 : ErrorTipsType.USER_IS_REGISTERED.getKey());
		map.put("result", isValid);
		return map;
	}

	@RequestMapping(value = "/checkEmail.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkEmail(String username, String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isValid = userService.checkEmail(email);
		map.put("message", isValid ? "" : ErrorTipsType.EMAIL_IS_REGISTERED.getDesc());
		map.put("errorCode", isValid ? -1 : ErrorTipsType.EMAIL_IS_REGISTERED.getKey());
		map.put("result", isValid);
		return map;
	}
	
	@RequestMapping(value = "/activate.do", method = RequestMethod.GET)
	@ResponseBody
	public boolean activate(String code) {
		boolean result = userService.activate(code);
		return result;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(HttpSession session, LoginForm form) {
		String username = form.getUsername();
		String password = form.getPassword();
		String actualCode = (String) session.getAttribute(ACTUAL_CODE);
		Map<String, Object> map = new HashMap<String, Object>();
		String message = "";
		String jumpUrl = "";
		boolean result = false;
		boolean isCodeValid;
		try {
			CheckUtil.checkCode(form.getCode(), actualCode);
			isCodeValid = true;
		} catch (Exception e) {
			isCodeValid = false;
			message = ErrorTipsType.CODE_ERROR.getDesc();
		}
		if (isCodeValid) {
			User user = userService.get(username, password);
			if (user != null) {
				session.setAttribute("username", username);
				session.setAttribute("userId", user.getUserId());
				// 设置session过期时间（单位秒）
				session.setMaxInactiveInterval(1800);
				jumpUrl = (String) session.getAttribute(JUMP_PAGE);
				session.removeAttribute(JUMP_PAGE);
				jumpUrl = StringUtils.defaultIfEmpty(jumpUrl, "");
				result = true;
				session.removeAttribute(ACTUAL_CODE);
			} else {
				message = ErrorTipsType.LOGIN_ERROR.getDesc();
			}
		}
		map.put("message", message);
		map.put("redirect", jumpUrl);
		map.put("result", result);
		return map;
	}
	
	@LoginRequired
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		response.sendRedirect("/index.do");
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) {
		String SAVE_PATH = "/data/wwwdata/img/";
		FileUtil.checkFolderPath(SAVE_PATH);
		List<String> urlList = new LinkedList<String>();
		Collection<Part> parts = null;
		try {
			request.setCharacterEncoding("UTF-8");
			parts = request.getParts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStream is = null;
		FileOutputStream fos = null;
		// 遍历所有的表单内容，将表单中的文件写入上传文件目录
		for (Iterator<Part> iterator = parts.iterator(); iterator.hasNext();) {
			Part part = iterator.next();
			// 从Part的content-disposition中提取上传文件的文件名
			String fileName = RequestUtil.getFileName(part);
			if (StringUtils.isNotEmpty(fileName)) {
				fileName = FileUtil.parseFileName(fileName);
				String savePath = SAVE_PATH + File.separator + fileName;
				System.out.println("savePath:" + savePath);
				urlList.add("www.goodancer.com/img/" + fileName);

				// inputStream转成outputStream并存储
				try {
					is = part.getInputStream();
					fos = new FileOutputStream(savePath);
					OutputStream optS = (OutputStream) fos;
					int c;
					while ((c = is.read()) != -1) {
						optS.write(c);
					}
					optS.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		IOUtils.closeQuietly(is);
		IOUtils.closeQuietly(fos);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileNames", urlList);
		// 显示上传的文件列表
		return map;
	}

	@RequestMapping(value = "/listUser.do", method = RequestMethod.GET)
	@ResponseBody
	public List<User> listUser() {
		List<User> list = userService.list(0, 10);
		return list;
	}

	@RequestMapping(value=" /async.do", method = RequestMethod.GET) 
	public void async(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("Servlet  begin <br>");
		System.out.println("servlet start");

		AsyncContext asyncContext = request.startAsync(request, response);
		
		
		BusinessHandleThread businessHandleThread = new BusinessHandleThread(asyncContext);
		Thread thread = new Thread(businessHandleThread);
		thread.start();
		// asyncContext.start(businessHandleThread);
		// 也可以用这种方法启动异步线程

		out.println("Servlet end <br>");
		System.out.println("servlet done");
		out.flush();
	}
	
	
}
