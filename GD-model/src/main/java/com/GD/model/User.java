package com.GD.model;

import java.util.Date;

public class User {
	private int userId;
	/** 用户名 */
	private String username;
	/** 密码 */
	private String password;
	/** 昵称 */
	private String nickname;
	/** 邮箱 */
	private String email;
	/** 保密问题 */
	private String question;
	/** 保密答案 */
	private String answer;
	/** 用户状态 */
	private int status;
	/** 性别 */
	private int sex;
	/** 权限等级 */
	private int role;
	/** 注册时间 */
	private Date posttime;
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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
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

}
