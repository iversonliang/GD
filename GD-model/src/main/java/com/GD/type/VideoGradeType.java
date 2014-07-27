package com.GD.type;

public enum VideoGradeType implements Inum{
	ALL(0, "不限"),
	RECOMMEND(1, "编辑推荐"),
	COMMON(2, "普通推荐")
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
