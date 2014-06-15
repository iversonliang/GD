package com.GD.util;

import java.security.InvalidParameterException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.GD.model.User;
import com.GD.model.Video;
import com.GD.type.ErrorTipsType;
import com.GD.type.VideoSourceType;
import com.GD.type.VideoType;

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
		CheckUtil.checkSex(user.getSex());
	}

	/**
	 * 检查更新用户资料参数
	 * 
	 * @param user
	 */
	public static void checkUpdateUserInfo(User user) {
		if (StringUtils.isNotEmpty(user.getSign())) {
			CheckUtil.checkSign(user.getSign());
		}
		if (StringUtils.isNotEmpty(user.getDescription())) {
			CheckUtil.checkDescription(user.getDescription());
		}
		if (user.getBirthday() != null) {
			CheckUtil.checkBirthday(user.getBirthday());
		}
		CheckUtil.checkSex(user.getSex());
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

	public static void checkSign(String sign) {
		if (StringUtils.isEmpty(sign)) {
			throw new InvalidParameterException(ErrorTipsType.SIGN_ERROR.getDesc());
		}
		String reg = "[^\\x00-\\xff]";
		int length = sign.replaceAll(reg, "xx").length();
		if (length > 38) {
			throw new InvalidParameterException(ErrorTipsType.SIGN_ERROR.getDesc());
		}
	}

	public static void checkDescription(String description) {
		if (StringUtils.isEmpty(description)) {
			throw new InvalidParameterException(ErrorTipsType.DESCRIPTION_ERROR.getDesc());
		}
		String reg = "[^\\x00-\\xff]";
		int length = description.replaceAll(reg, "xx").length();
		if (length > 200) {
			throw new InvalidParameterException(ErrorTipsType.DESCRIPTION_ERROR.getDesc());
		}
	}

	public static void checkBirthday(Date birthday) {
		Date startDate = DateUtil.str2Date("1970-01-01 00:00:00");
		Date endDate = DateUtil.str2Date("2010-12-31 23:59:59");
		if (DateUtil.before(birthday, startDate) || DateUtil.before(endDate, birthday)) {
			throw new InvalidParameterException(ErrorTipsType.BIRTHDAY_ERROR.getDesc());
		}
	}

	public static void checkSex(int sex) {
		if (!(sex == 0 || sex == 1)) {
			throw new InvalidParameterException(ErrorTipsType.SEX_ERROR.getDesc());
		}
	}

	/**
	 * 用户发布时检查视频参数
	 * 
	 * @param video
	 */
	public static void checkVideo(Video video) {
		if (StringUtils.isEmpty(video.getUrl())) {
			throw new InvalidParameterException(ErrorTipsType.VIDEO_URL_ERROR.getDesc());
		}
		if (StringUtils.isEmpty(video.getName())) {
			throw new InvalidParameterException(ErrorTipsType.VIDEO_NAME_ERROR.getDesc());
		}
		try {
			VideoType.toType(video.getVideoType());
		} catch (Exception e) {
			throw new InvalidParameterException(ErrorTipsType.VIDEO_TYPE_ERROR.getDesc());
		}
		try {
			VideoSourceType.toType(video.getVideoSourceType());
		} catch (Exception e) {
			throw new InvalidParameterException(ErrorTipsType.VIDEO_SOURCE_TYPE_ERROR.getDesc());
		}
	}
}
