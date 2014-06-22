package com.GD.util;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.GD.type.ErrorTipsType;

/**
 * 检查正则
 * 
 * @author iversonLiang
 * 
 */
public class RegularUtil {
	/**
	 * 验证邮箱地址是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static void checkEmail(String email) {
		String reg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		boolean result = RegularUtil.match(reg, email);
		if (!result) {
			throw new InvalidParameterException(ErrorTipsType.EMAIL_ERROR.getDesc());
		}
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobile
	 * @return
	 */
	public static void checkMobile(String mobile) {
		String reg = "^[1][3,4,5,8][0-9]{9}$";
		boolean result = RegularUtil.match(reg, mobile);
		if (!result) {
			throw new InvalidParameterException(ErrorTipsType.MOBILE_ERROR.getDesc());
		}
	}

	/**
	 * 检查用户密码是否字母+数字，6-16位
	 * 
	 * @param password
	 * @return
	 */
	public static void checkPassword(String password) {
		String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
		boolean result = RegularUtil.match(reg, password);
		if (!result) {
			throw new InvalidParameterException(ErrorTipsType.PASSWORD_ERROR.getDesc());
		}
	}

	/**
	 * 检查用户名，6-16位
	 * 
	 * @param username
	 */
	public static void checkUsername(String username) {
		String reg = "^[0-9A-Za-z]{6,16}$";
		boolean result = RegularUtil.match(reg, username);
		if (!result) {
			throw new InvalidParameterException(ErrorTipsType.USERNAME_ERROR.getDesc());
		}
	}

	private static boolean match(String reg, String checkStr) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(checkStr);
		return matcher.matches();
	}

	/**
	 * 检查字符长度
	 * 
	 * @param str
	 * @param length
	 * @param type
	 */
	public static void checkStrLength(String str, int length, ErrorTipsType type) {
		String reg = "[^\\x00-\\xff]";
		int currLength = str.replaceAll(reg, "xx").length();
		if (currLength > length) {
			throw new InvalidParameterException(type.getDesc());
		}
	}
}
