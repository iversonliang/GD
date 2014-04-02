package com.GD.handler.impl;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.GD.handler.UserHandler;
import com.GD.model.User;
import com.GD.type.UserStatusType;
import com.GD.web.form.UserForm;

@Component
public class UserHandlerImpl implements UserHandler {

	@Override
	public User form2User(UserForm form) {
		User user = new User();
		user.setAnswer(form.getAnswer());
		user.setEmail(form.getEmail());
		user.setNickname(form.getNickname());
		user.setPassword(form.getPassword());
		user.setPosttime(new Date());
		user.setQuestion(form.getQuestion());
		user.setStatus(UserStatusType.UNACTIVATED.getKey());
		user.setUsername(form.getUsername());
		return user;
	}

}
