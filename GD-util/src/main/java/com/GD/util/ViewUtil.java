package com.GD.util;

import org.springframework.web.servlet.ModelAndView;

public class ViewUtil {
	public static ModelAndView getSpecifiedView(String dir, String methodName) {
		ModelAndView model = new ModelAndView(dir + "/" + methodName);
		return model;
	}

	public static ModelAndView getView(String dir) {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		String methodName = stacks[2].getMethodName();
		return ViewUtil.getSpecifiedView(dir, methodName);
	}

//	public static ModelAndView getTextView(String message) {
//		ModelAndView model = new ModelAndView("message");
//		model.addObject("message", message);
//		return model;
//	}

//	public static ModelAndView getJsonView(int num) {
//		String json = getJson(num);
//		return ViewUtil.getJsonView(json);
//	}

//	private static String getJson(Object result) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("result", result);
//		map.put("errorCode", "200");
//		String json = Json.toJson(map);
//		return json;
//	}

//	public static ModelAndView getJsonView(Object success) {
//		String json = getJson(success);
//		return ViewUtil.getJsonView(json);
//	}

	public static ModelAndView getJsonView(String json) {
		ModelAndView model = new ModelAndView("message.json");
		model.addObject("message", json);
		return model;
	}
}
