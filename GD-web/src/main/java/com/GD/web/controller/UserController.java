package com.GD.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
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
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.GD.handler.UserHandler;
import com.GD.interceptor.LoginRequired;
import com.GD.model.User;
import com.GD.service.UserService;
import com.GD.util.AuthCodeUtil;
import com.GD.util.FileUtil;
import com.GD.web.form.UserForm;

@Controller
@RequestMapping(value = UserController.DIR)
public class UserController {

	@Autowired
	private UserHandler userHandler;
	@Autowired
	private UserService userService;

	public static final String DIR = "/user";

	final private String format = "image/png";

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
		// 禁止图像缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		response.setContentType("image/jpeg");
		return new ResponseEntity<byte[]>(tileBytes, responseHeaders, HttpStatus.OK);
	}

	/**
	 * 校验验证码
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
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
		session.removeAttribute("actualCode");
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

	@RequestMapping(value = "/activate.do", method = RequestMethod.GET)
	@ResponseBody
	public boolean activate(String code) {
		boolean result = userService.activate(code);
		return result;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		// User user = userService.get(username, password);
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String code = (String) request.getParameter("code");
		System.out.println("login username:" + username + " password:" + password + " code:" + code);
		Object actualCode = session.getAttribute("actualCode");
		boolean result = false;
		if (code != null && actualCode != null && code.equalsIgnoreCase(actualCode.toString())) {
			result = true;
		}
		session.removeAttribute("actualCode");
		System.out.println("actualCode:" + actualCode.toString() + " result:" + result);
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

	@LoginRequired
	@RequestMapping(value = "/testLogin")
	public ModelAndView testLogin(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("------testLogin---------- " + request.getSession().getAttribute("test") + " " + request.getParameter("test"));
		ModelAndView model = new ModelAndView("success");
		// if (StringUtils.isEmpty(result) || !result.equals("logined")) {
		// model.setViewName("loginPage");
		// }
		return model;
	}

	@RequestMapping(value = "/testLoginPage", method = RequestMethod.GET)
	public ModelAndView testLoginPage() {
		ModelAndView model = new ModelAndView("testLoginPage");
		return model;
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
	public Map upload(HttpServletRequest request, HttpServletResponse response) {
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
			String fileName = getFileName(part);
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

	/**
	 * 从Part的Header信息中提取上传文件的文件名
	 * 
	 * @param part
	 * @return 上传文件的文件名，如果如果没有则返回null
	 */
	private String getFileName(Part part) {
		String fileNameExtractorRegex = "filename=\".+\"";
		// 获取header信息中的content-disposition，如果为文件，则可以从其中提取出文件名
		String header = part.getHeader("content-disposition");
		String fileName = null;
		Pattern pattern = Pattern.compile(fileNameExtractorRegex);
		Matcher matcher = pattern.matcher(header);
		if (matcher.find()) {
			fileName = matcher.group();
			fileName = fileName.substring(10, fileName.length() - 1);
		}
		return fileName;
	}
	
	@RequestMapping(value = "/listUser.do", method = RequestMethod.GET)
	@ResponseBody
	public List<User> listUser() {
		List<User> list = userService.list(0, 10);
		return list;
	}

}
