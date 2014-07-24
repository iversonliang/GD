package com.GD.web.form;

public class VideoSearchForm {

	private Integer statusType;
	private Integer videoType;
	private Integer homeType;
	private Integer videoGradeType;
	private Integer videoSourceType;
	private Integer sortType;
	private String name;
	private String label;

	public Integer getSortType() {
		return sortType;
	}

	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}

	public Integer getVideoType() {
		return videoType;
	}

	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
	}

	public Integer getStatusType() {
		return statusType;
	}

	public void setStatusType(Integer statusType) {
		this.statusType = statusType;
	}

	public Integer getHomeType() {
		return homeType;
	}

	public void setHomeType(Integer homeType) {
		this.homeType = homeType;
	}

	public Integer getVideoGradeType() {
		return videoGradeType;
	}

	public void setVideoGradeType(Integer videoGradeType) {
		this.videoGradeType = videoGradeType;
	}

	public Integer getVideoSourceType() {
		return videoSourceType;
	}

	public void setVideoSourceType(Integer videoSourceType) {
		this.videoSourceType = videoSourceType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
