package com.GD.type;

public enum RoleType implements Inum{
	USER(0, "正常"),
	ADMIN(1, "管理员"),
	SUPER_ADMIN(2, "超级管理员")
	;

	private int key;
	private String desc;
	
	private RoleType(int key, String desc) {
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

	public static RoleType toType(Integer key) {
		return EnumUtil.toEnum(key, RoleType.class);
	}
}
