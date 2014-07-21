package com.GD.type;

public enum VideoSourceType implements Inum{
	ALL(0, "全部"),
	ORIGINAL(1, "原创"),
	REPRINT(2, "转载")
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
