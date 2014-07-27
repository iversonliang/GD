package com.GD.type;

public enum TimeLimitType implements Inum{
	ALL(0, "不限"),
	THREE_DAY(1, "三天内"),
	WEEK(2, "一周内"),
	MONTH(3, "一个月内"),
	YEAR(4, "一年内")
	;

	private int key;
	private String desc;
	
	private TimeLimitType(int key, String desc) {
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

	public static TimeLimitType toType(Integer key) {
		return EnumUtil.toEnum(key, TimeLimitType.class);
	}
}
