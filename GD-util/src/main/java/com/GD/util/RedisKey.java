package com.GD.util;

public class RedisKey {

	/**
	 * 用户待激活Key
	 * 
	 * @param code
	 * @return
	 */
	public static String getUserActivate(String code) {
		return "user_activate:" + code;
	}
}
