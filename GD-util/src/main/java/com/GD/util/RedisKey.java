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

	/**
	 * 活跃用户
	 * 
	 * @return
	 */
	public static String getActiveUser() {
		return "z_active_user";
	}

	/**
	 * 用户头像
	 * 
	 * @return
	 */
	public static String getUserHeadImg() {
		return "h_user_head_img";
	}
}
