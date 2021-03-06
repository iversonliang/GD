package com.GD.type;


public enum UserStatusType implements Inum{
	UNACTIVATED(0, "未激活"),
	ACTIVATED(1, "已激活")
	;

	private int key;
	private String desc;
	
	private UserStatusType(int key, String desc) {
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

	public static UserStatusType toType(Integer key) {
		return EnumUtil.toEnum(key, UserStatusType.class);
	}
}
