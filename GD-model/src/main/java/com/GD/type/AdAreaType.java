package com.GD.type;

public enum AdAreaType implements Inum{
	IGNORE(0, "忽略"),
	SLIDE(1, "幻灯片"),
	SLIDE_BEHIND(2, "幻灯片后面")
	;

	private int key;
	private String desc;
	
	private AdAreaType(int key, String desc) {
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

	public static AdAreaType toType(Integer key) {
		return EnumUtil.toEnum(key, AdAreaType.class);
	}
}
