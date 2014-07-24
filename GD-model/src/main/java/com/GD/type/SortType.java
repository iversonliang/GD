package com.GD.type;

public enum SortType implements Inum{
	ALL(0, "不限"),
	POSTTIME(1, "最新上传"),
	PLAY(2, "观看最多"),
	LOVE(3, "喜欢最多"),
	COMMENTS(4, "评论最多")
	;

	private int key;
	private String desc;
	
	private SortType(int key, String desc) {
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

	public static SortType toType(Integer key) {
		return EnumUtil.toEnum(key, SortType.class);
	}
}
