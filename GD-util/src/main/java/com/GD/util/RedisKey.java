package com.GD.util;

public class RedisKey {

	/**
	 * 用户待激活Key
	 * 
	 * @param code
	 * @return
	 */
	public static String getUserActive(String code) {
		return "user_activate:" + code;
	}
	
	/**
	 * 用户重置密码Key
	 * 
	 * @param code
	 * @return
	 */
	public static String getUserReset(String code) {
		return "user_reset:" + code;
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

	/**
	 * 防刷视频人气
	 * 
	 * @param sessionId
	 * @param videoId
	 * @return
	 */
	public static String getDefenseFlush(String sessionId, int videoId) {
		return "k_" + sessionId + ":" + videoId;
	}

	/**
	 * 临时上传文件
	 * 
	 * @param imgType
	 * @return
	 */
	public static String getTempImg(String imgType) {
		return "z_" + imgType;
	}

	/**
	 * 喜欢视频
	 * 
	 * @param userId
	 * @return
	 */
	public static String getLikeVideo(int userId) {
		return "z_like_video:" + userId;
	}

	/**
	 * 获取用户消息提示数量
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserLastReadMessage(int userId) {
		return "h_message:" + userId;
	}
}
