package com.GD.type;

public enum SourceSiteType implements Inum{
	ALL(0, "全部"),
	YOUKU(1, "优酷"),
	TUDOU(2, "土豆"),
	SOHU(3, "搜狐"),
	TENCENT(4, "腾讯")
	;

	private int key;
	private String desc;
	
	private SourceSiteType(int key, String desc) {
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

	public static SourceSiteType toType(Integer key) {
		return EnumUtil.toEnum(key, SourceSiteType.class);
	}
	
}
