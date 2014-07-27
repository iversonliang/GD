package com.GD.handler.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.GD.handler.UserHandler;
import com.GD.model.User;
import com.GD.model.Video;
import com.GD.service.UserService;
import com.GD.service.VideoService;
import com.GD.type.RoleType;
import com.GD.type.UserStatusType;
import com.GD.util.DateUtil;
import com.GD.util.Static;
import com.GD.web.form.UserForm;
import com.GD.web.vo.UserVO;

@Component
public class UserHandlerImpl implements UserHandler {
	

	@Autowired
	private VideoService videoService;
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
		user.setVideoCount(0);
		user.setProvince(StringUtils.defaultIfEmpty(form.getProvince(), ""));
		user.setCity(StringUtils.defaultIfEmpty(form.getCity(), ""));
		
		return user;
	}

	@Override
	public User getUpdateUser(User oldUser, UserForm form) {
		User newUser = (User) ObjectUtils.clone(oldUser);
		if (StringUtils.isNotEmpty(form.getSign())) {
			newUser.setSign(form.getSign());
		}
		if (StringUtils.isNotEmpty(form.getRealName())) {
			newUser.setRealName(form.getRealName());
		}
		if (StringUtils.isNotEmpty(form.getDescription())) {
			newUser.setDescription(form.getDescription());
		}
		newUser.setDanceType(form.getDanceType());
		if (form.getSex() == 0 || form.getSex() == 1) {
			newUser.setSex(form.getSex());
		}
		Date birthday = Static.DEFAULT_DATE;
		if (StringUtils.isNotEmpty(form.getBirthday())) {
			birthday = DateUtil.str2Date(form.getBirthday());
		}
		if (StringUtils.isNotEmpty(form.getProvince())) {
			newUser.setProvince(form.getProvince());
		}
		if (StringUtils.isNotEmpty(form.getCity())) {
			newUser.setCity(form.getCity());
		}
		newUser.setBirthday(birthday);
		return newUser;
	}

	@Override
	public List<UserVO> toVoList(List<User> list) {
		List<UserVO> voList = new ArrayList<UserVO>();
		for (User user : list) {
			List<Video> videoList = videoService.list(user.getUserId(), 0, 3);
			UserVO vo = new UserVO();
			try {
				BeanUtils.copyProperties(vo, user);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			vo.setVideoList(videoList);
			voList.add(vo);
		}
		return voList;
	}

}
