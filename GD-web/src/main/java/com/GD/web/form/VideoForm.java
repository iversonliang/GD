package com.GD.web.form;

public class VideoForm {

	/** 视频名称 */
	private String name;
	/** 描述 */
	private String description;
	/** 视频地址 */
	private String url;
	/** 标签 */
	private String label;
	/** 视频类型 */
	private int type;
	/** 视频来源类型 */
	private int sourceType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

}
