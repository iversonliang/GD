package com.GD.util;

import java.io.File;
import java.util.Date;

public class FileUtil {
	
	private static int UPLOAD_COUNT = 0;

	/**
	 * 检查文件夹路径，没有则创建
	 * 
	 * @param filePath
	 */
	public static void checkFolderPath(String filePath) {
		File folder = new File(filePath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}
	
	public static String parseFileName(String fileName) {
		String[] temp = fileName.split("\\.");
		String name = DateUtil.date2String(new Date(), "yyMMddHHmmss");
		name += FileUtil.getUploadCount();
		return name + "." + temp[1];
	}
	
	
	private static int getUploadCount() {
		UPLOAD_COUNT++;
		if (UPLOAD_COUNT == 100) {
			UPLOAD_COUNT = 1;
		}
		return UPLOAD_COUNT;
	}
}
