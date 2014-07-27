package com.GD.handler;

import java.util.List;

import com.GD.model.User;
import com.GD.web.form.UserForm;
import com.GD.web.vo.UserVO;

public interface UserHandler {
	
	public User form2User(UserForm form);
	
	public User getUpdateUser(User oldUser, UserForm form);
	
	public List<UserVO> toVoList(List<User> list);
}
