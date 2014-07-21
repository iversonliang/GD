package com.GD.type;

public enum NoticeType implements Inum{
	HOME_RECOMMEND(10, "首页推荐")
	;

	private int key;
	private String desc;
	
	private NoticeType(int key, String desc) {
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

	public static NoticeType toType(Integer key) {
		return EnumUtil.toEnum(key, NoticeType.class);
	}
}
