package com.GD.type;

public enum ErrorTipsType implements Inum{
	USERNAME_ERROR(1, "用户名需要在6-16位以内的字母或数字"),
	LOGIN_ERROR(2, "用户名或密码错误"),
	CODE_ERROR(3, "验证码错误"),
	SEX_ERROR(4, "性别错误"),
	MOBILE_ERROR(5, "手机号码错误"),
	PASSWORD_ERROR(6, "用户密码必须是数字和字母组合，在6-16位以内"),
	EMAIL_ERROR(7, "邮箱格式错误"),
	USER_IS_REGISTERED(8, "用户名已经被注册"),
	EMAIL_IS_REGISTERED(9, "邮箱已经被注册"),
	LOGIN_FAIL(10, "注册失败，请稍后再试"),
	SIGN_ERROR(11, "签名长度需要在1-38位以内"),
	DESCRIPTION_ERROR(12, "个人简介长度需要在1-200位以内"),
	BIRTHDAY_ERROR(13, "错误生日时间"),
	UPLOAD_ERROR(14, "上传失败"),
	RESET_PASSWORD_ERROR(15, "密码错误"),
	VIDEO_NAME_ERROR(16, "视频标题错误"),
	VIDEO_URL_ERROR(17, "视频地址错误"),
	VIDEO_TYPE_ERROR(18, "视频类型错误"),
	VIDEO_SOURCE_TYPE_ERROR(19, "视频来源类型错误");

	private int key;
	private String desc;
	
	private ErrorTipsType(int key, String desc) {
		this.key = key;
		this.desc = desc;
	}
	
	@Override
	public String getDesc() {
		return this.desc;
	}

	@Override
	public Integer getKey() {
		return this.key;
	}

	public static ErrorTipsType toType(Integer key) {
		return EnumUtil.toEnum(key, ErrorTipsType.class);
	}
	
	public static ErrorTipsType toType(String desc) {
		for (ErrorTipsType type : ErrorTipsType.values()) {
			if (type.getDesc().equals(desc)) {
				return type;
			}
		}
		throw new IllegalArgumentException("枚举元素[" + desc + "]不存在.");
	}
}
