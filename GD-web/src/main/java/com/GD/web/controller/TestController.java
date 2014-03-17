package com.GD.web.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
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

import com.GD.service.TestService;
import com.GD.util.AuthCodeUtil;

@Controller
public class TestController {
	
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

	
	@RequestMapping(value = "test")
	public ModelAndView test() {
		System.out.println("------------test--------------");
		System.out.println("testService:" + testService);
		testService.test();
		ModelAndView view = new ModelAndView("index");
		return view;
	}
}
