package com.GD.type;

public enum VideoGradeType implements Inum{
	RECOMMEND(0, "ÍÆ¼ö"),
	COMMON(1, "ÆÕÍ¨")
	;

	private int key;
	private String desc;
	
	private VideoGradeType(int key, String desc) {
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

	public static VideoGradeType toType(Integer key) {
		return EnumUtil.toEnum(key, VideoGradeType.class);
	}
}
