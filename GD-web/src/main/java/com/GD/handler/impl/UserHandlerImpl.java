package com.GD.handler.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.GD.handler.UserHandler;
import com.GD.model.User;
import com.GD.type.RoleType;
import com.GD.type.UserStatusType;
import com.GD.util.DateUtil;
import com.GD.util.Static;
import com.GD.web.form.UserForm;

@Component
public class UserHandlerImpl implements UserHandler {

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

}
