package com.GD.type;

public enum VideoType implements Inum{
	ALL(0, "全部"),
	CHOREOGRAPHY(1, "编舞"),
	SOLO(2, "SOLO"),
	BATTLE(3, "BATTLE"),
	TEACH(4, "教学"),
	INTERVIEW(5, "采访"),
	VIDEO(6, "电影")
	;

	private int key;
	private String desc;
	
	private VideoType(int key, String desc) {
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

	public static VideoType toType(Integer key) {
		return EnumUtil.toEnum(key, VideoType.class);
	}
}
