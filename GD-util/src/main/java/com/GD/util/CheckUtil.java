package com.GD.util;

import java.security.InvalidParameterException;

import com.GD.model.User;
import com.GD.type.ErrorTipsType;

public class CheckUtil {

	/**
	 * 检查注册参数
	 * 
	 * @param user
	 */
	public static void checkRegisterUser(User user) {
		RegularUtil.checkEmail(user.getEmail());
		RegularUtil.checkPassword(user.getPassword());
		RegularUtil.checkUsername(user.getUsername());
		if (!(user.getSex() == 0 || user.getSex() == 1)) {
			throw new InvalidParameterException(ErrorTipsType.SEX_ERROR.getDesc());
		}
	}

	/**
	 * 校验验证码
	 * 
	 * @param code
	 *            输入的验证码
	 * @param actualCode
	 *            正确的验证码
	 * @return
	 */
	public static void checkCode(String code, String actualCode) {
		boolean result = false;
		if (code != null && actualCode != null && code.equalsIgnoreCase(actualCode)) {
			result = true;
		}
		if (!result) {
			throw new InvalidParameterException(ErrorTipsType.CODE_ERROR.getDesc());
		}
	}
}
