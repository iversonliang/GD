package com.GD.model;

import java.io.Serializable;

public class CaptureVideo implements Serializable {

	private static final long serialVersionUID = -6220973207083491817L;

	private String title = "";// 视频标题
	private String thumbnail = "";// 视频缩略图
	private String summary = "";// 视频简介
	private String time = "";// 视频时长
	private String source = "";// 视频来源
	private String pageUrl = "";// 视频页面地址
	private String flashUrl = "";// 视频FLASH地址
	private String htmlCode = "";// 视频HTML代码

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getFlashUrl() {
		return flashUrl;
	}

	public void setFlashUrl(String flashUrl) {
		this.flashUrl = flashUrl;
	}

	public String getHtmlCode() {
		return htmlCode;
	}

	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
