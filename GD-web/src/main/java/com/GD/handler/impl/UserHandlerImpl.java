package com.GD.handler.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.GD.handler.UserHandler;
import com.GD.model.User;
import com.GD.service.UserService;
import com.GD.type.RoleType;
import com.GD.type.UserStatusType;
import com.GD.util.DateUtil;
import com.GD.util.Static;
import com.GD.web.form.UserForm;

@Component
public class UserHandlerImpl implements UserHandler {
	
	@Autowired
	private UserService userService;

	@Override
	public User form2User(UserForm form) {
		User user = new User();
		user.setAnswer(StringUtils.defaultIfEmpty(form.getAnswer(), ""));
		Date birthday = Static.DEFAULT_DATE;
		if (StringUtils.isNotEmpty(form.getBirthday())) {
			birthday = DateUtil.str2Date(form.getBirthday());
		}
		user.setBirthday(birthday);
		user.setCity(form.getCity());
		user.setDanceType(StringUtils.defaultIfEmpty(form.getDanceType(), ""));
		user.setDescription(StringUtils.defaultIfEmpty(form.getDescription(), ""));
		user.setEmail(form.getEmail());
		user.setNickname(StringUtils.defaultIfEmpty(form.getNickname(), ""));
		user.setPassword(form.getPassword());
		user.setPosttime(new Date());
		user.setProvince(form.getProvince());
		user.setQuestion(form.getQuestion());
		user.setRealName(StringUtils.defaultIfEmpty(form.getRealName(), ""));
		user.setRole(RoleType.USER.getKey());
		user.setSex(form.getSex());
		user.setStatus(UserStatusType.UNACTIVATED.getKey());
		user.setUsername(form.getUsername());
		
		return user;
	}

	@Override
	public User getUpdateUser(int userId, UserForm form) {
		User user = userService.get(userId);
		if (StringUtils.isNotEmpty(form.getSign())) {
			user.setSign(form.getSign());
		}
		if (StringUtils.isNotEmpty(form.getRealName())) {
			user.setRealName(form.getRealName());
		}
		if (StringUtils.isNotEmpty(form.getDescription())) {
			user.setDescription(form.getDescription());
		}
		user.setDanceType(form.getDanceType());
		if (form.getSex() == 0 || form.getSex() == 1) {
			user.setSex(form.getSex());
		}
		Date birthday = Static.DEFAULT_DATE;
		if (StringUtils.isNotEmpty(form.getBirthday())) {
			birthday = DateUtil.str2Date(form.getBirthday());
		}
		user.setBirthday(birthday);
		return user;
	}

}
