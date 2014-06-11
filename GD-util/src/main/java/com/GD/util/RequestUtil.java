package com.GD.util;

import java.net.URLDecoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

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
	
	/**
	 * 从Part的Header信息中提取上传文件的文件名
	 * 
	 * @param part
	 * @return 上传文件的文件名，如果如果没有则返回null
	 */
	public static String getFileName(Part part) {
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
	
}
