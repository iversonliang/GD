package com.GD.type;

public enum GroupType implements Inum{
	SINGLE(1, "个人"),
	CREW(2, "团体")
	;

	private int key;
	private String desc;
	
	private GroupType(int key, String desc) {
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

	public static GroupType toType(Integer key) {
		return EnumUtil.toEnum(key, GroupType.class);
	}
}
