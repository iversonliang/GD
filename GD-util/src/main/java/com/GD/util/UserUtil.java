package com.GD.util;

import com.GD.model.User;
import com.GD.type.RoleType;

public class UserUtil {

	public static void checkNull(User user) {
		if (user == null) {
			throw new RuntimeException("用户为空");
		}
	}
	
	public static void checkAdminAuthority(User user) {
		if (RoleType.toType(user.getRole()).getKey() == RoleType.USER.getKey()) {
			throw new RuntimeException("用户[" + user.getUserId() + "] 不是系统管理员"); // 管理员
		}
	}
}
