package com.GD.type;

public enum VideoType implements Inum{
	CHOREOGRAPHY(0, "编舞"),
	SOLO(1, "SOLO"),
	BATTLE(2, "BATTLE"),
	TEACH(3, "教学"),
	INTERVIEW(4, "专访"),
	VIDEO(5, "电影")
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
