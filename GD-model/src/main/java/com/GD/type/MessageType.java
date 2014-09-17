package com.GD.type;

public enum MessageType implements Inum{
	TO_MY_COMMENT(0, "给我的评论"),
	REPLY_TO_ME(1, "给我的回复"),
	NOTICE(2, "系统通知"),
	ANNOUNCEMENT(3, "系统公告")
	;

	private int key;
	private String desc;
	
	private MessageType(int key, String desc) {
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

	public static MessageType toType(Integer key) {
		return EnumUtil.toEnum(key, MessageType.class);
	}
}
