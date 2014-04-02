package com.GD.util;

public class RedisKey {

	/**
	 * ÓÃ»§´ı¼¤»îKey
	 * 
	 * @param code
	 * @return
	 */
	public static String getUserActivate(String code) {
		return "user_activate:" + code;
	}
}
