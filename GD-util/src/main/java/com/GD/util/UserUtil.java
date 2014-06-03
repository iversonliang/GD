package com.GD.util;

import com.GD.model.User;

public class UserUtil {

	public static void checkNull(User user) {
		if (user == null) {
			throw new RuntimeException("用户为空");
		}
	}
}
