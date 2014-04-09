package com.GD.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang.StringUtils;

//@WebServlet(name = "FileUploadServlet", urlPatterns = { "/upload.do" })
//@MultipartConfig(location = FileUploadServlet.SAVE_PATH, fileSizeThreshold = 1024)
public class FileUploadServlet extends HttpServlet {
	protected static final String SAVE_PATH = "D:\\upload";

	private static final long serialVersionUID = 1L;
	private String fileNameExtractorRegex = "filename=\".+\"";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new UnsupportedOperationException();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> fileNames = new LinkedList<String>();
		request.setCharacterEncoding("UTF-8");
		Collection<Part> parts = request.getParts();
		// 遍历所有的表单内容，将表单中的文件写入上传文件目录
		for (Iterator<Part> iterator = parts.iterator(); iterator.hasNext();) {
			Part part = iterator.next();
			// 从Part的content-disposition中提取上传文件的文件名
			String fileName = getFileName(part);
			if (StringUtils.isNotEmpty(fileName)) {
				String savePath = SAVE_PATH + File.separator + fileName;
				System.out.println("savePath:" + savePath);
				fileNames.add(fileName);
				part.write(fileName);

			}
		}
		request.setAttribute("fileNames", fileNames);
		// 显示上传的文件列表
		request.getRequestDispatcher("/WEB-INF/jsp/uploadSuccess.jsp").forward(request, response);
	}

	/**
	 * 从Part的Header信息中提取上传文件的文件名
	 * 
	 * @param part
	 * @return 上传文件的文件名，如果如果没有则返回null
	 */
	private String getFileName(Part part) {
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

	public void init(ServletConfig filterConfig) throws ServletException {
		System.out.println("servlet init");
	}

	public void destroy() {
	}
}
