package com.GD.util;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	/** */
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getRequestURL(HttpServletRequest request) throws Exception {
//		String url = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + request.getServletPath();
		String url = request.getContextPath() + request.getServletPath();
		Map<String, String[]> params = request.getParameterMap();  
		if (params == null || params.size() == 0) {
			return url;
		}
		url += "?";
		for (String key : params.keySet()) {  
            String[] values = params.get(key);  
            for (int i = 0; i < values.length; i++) {  
                String value = values[i];  
                url += key + "=" + value + "&";  
            }  
        }  
        url = url.substring(0, url.length() - 1);
        return URLDecoder.decode(url, "utf-8");
//        return URLEncoder.encode(url, "utf-8");
	}
	
}
