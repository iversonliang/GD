package com.GD.type;

public enum VideoSourceType implements Inum{
	ORIGINAL(0, "原创"),
	REPRINT(1, "装载")
	;

	private int key;
	private String desc;
	
	private VideoSourceType(int key, String desc) {
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

	public static VideoSourceType toType(Integer key) {
		return EnumUtil.toEnum(key, VideoSourceType.class);
	}
}
