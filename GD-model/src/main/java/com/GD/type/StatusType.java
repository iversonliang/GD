package com.GD.type;

public enum StatusType implements Inum{
	UNACTIVATED(0, "未激活"),
	NORMAL(1, "正常"),
	SHIELDING(2, "屏蔽")
	;

	private int key;
	private String desc;
	
	private StatusType(int key, String desc) {
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

	public static StatusType toType(Integer key) {
		return EnumUtil.toEnum(key, StatusType.class);
	}
}
