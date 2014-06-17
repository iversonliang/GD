package com.GD.web.vo;

import java.util.Date;
import java.util.List;

import com.GD.model.Video;

public class UserVO {
	private int userId;
	/** 用户名 */
	private String username;
	/** 昵称 */
	private String nickname;
	/** 性别 */
	private int sex;
	/** 城市 */
	private String city;
	/** 省份 */
	private String province;
	/** 真实姓名 */
	private String realName;
	/** 生日 */
	private Date birthday;
	/** 舞种 */
	private String danceType;
	/** 个人简介 */
	private String description;
	/** 个性签名 */
	private String sign;
	/** 头像地址 */
	private String headImg;
	/** 作品数量 */
	private int videoCount;
	/** 作品列表 */
	private List<Video> videoList;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDanceType() {
		return danceType;
	}

	public void setDanceType(String danceType) {
		this.danceType = danceType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public int getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}

	public List<Video> getVideoList() {
		return videoList;
	}

	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}

}
