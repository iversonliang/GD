package com.GD.type;

public enum InviteCodeStatusType implements Inum{
	NOT_SEND(0, "未发放"),
	SEND(1, "已发放"),
	USE(2, "已使用")
	;

	private int key;
	private String desc;
	
	private InviteCodeStatusType(int key, String desc) {
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

	public static InviteCodeStatusType toType(Integer key) {
		return EnumUtil.toEnum(key, InviteCodeStatusType.class);
	}

}
