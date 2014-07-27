package com.GD.type;

public enum HomeType implements Inum{
	IGNORE(0, "忽略"),
	RECOMMEND(1, "首页推荐"),
	SLIDE(2, "幻灯片"),
	SLIDE_BEHIND(3, "幻灯片后面")
	;

	private int key;
	private String desc;
	
	private HomeType(int key, String desc) {
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

	public static HomeType toType(Integer key) {
		return EnumUtil.toEnum(key, HomeType.class);
	}
}
