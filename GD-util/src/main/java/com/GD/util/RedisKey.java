package com.GD.util;

public class RedisKey {

	/**
	 * �û�������Key
	 * 
	 * @param code
	 * @return
	 */
	public static String getUserActivate(String code) {
		return "user_activate:" + code;
	}
}
