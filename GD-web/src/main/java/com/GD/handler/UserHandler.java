package com.GD.handler;

import com.GD.model.User;
import com.GD.web.form.UserForm;

public interface UserHandler {
	
	public User form2User(UserForm form);
	
	public User getUpdateUser(int userId, UserForm form);
	
}
