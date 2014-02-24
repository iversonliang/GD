package com.GD.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.GD.service.TestService;

@Controller
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(value = "test")
	public ModelAndView test() {
		System.out.println("------------test--------------");
		System.out.println("testService:" + testService);
		testService.test();
		ModelAndView view = new ModelAndView("index");
		return view;
	}
}
