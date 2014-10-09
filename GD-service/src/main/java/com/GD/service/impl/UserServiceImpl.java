package com.GD.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.GD.dao.ActiveUserDao;
import com.GD.dao.UserActivateDao;
import com.GD.dao.UserDao;
import com.GD.dao.UserHeadImgDao;
import com.GD.dao.UserResetDao;
import com.GD.email.MailInfo;
import com.GD.email.MailSender;
import com.GD.model.User;
import com.GD.service.UserService;
import com.GD.type.ErrorTipsType;
import com.GD.type.RoleType;
import com.GD.type.UserStatusType;
import com.GD.util.CodeUtil;
import com.GD.util.Constants;
import com.GD.util.EmailUtil;
import com.GD.util.ListUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserHeadImgDao userHeadImgDao;
	@Autowired
	private ActiveUserDao activeUserDao;
	@Autowired
	private UserResetDao userResetDao;
	@Autowired
	private UserActivateDao userActivateDao;
	@Autowired
	private UserDao userDao;

	@Override
	public int add(User user) {
		long result = userDao.add(user);

//		if (result > 0) {
//
//			String code = CodeUtil.generateString(50);
//			userActivateDao.add((int)result, code);
//			MailInfo mailInfo = EmailUtil.getRegistMailInfo(user.getEmail(), code);
//			MailSender.sendHtmlMail(mailInfo);// 发送html格式
//		}
		return (int) result;
	}

	@Override
	public User get(String username, String password) {
		return userDao.get(username, password);
	}

	@Override
	public boolean updateStatus(int userId, UserStatusType type) {
		User oldUser = this.get(userId);
		User newUser = this.copyUser(oldUser);
		newUser.setStatus(type.getKey());
		return this.update(newUser, oldUser);
//		return userDao.updateStatus(userId, type.getKey());
	}
	
	private User copyUser(User oldUser) {
		User newUser = new User();
		try {
			BeanUtils.copyProperties(newUser, oldUser);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return newUser;
	} 

	@Override
	public boolean activate(String code) {
		int userId = userActivateDao.get(code);
		if (userId == 0) {
			return false;
		}
		User oldUser = this.get(userId);
		User newUser = this.copyUser(oldUser);
		newUser.setStatus(UserStatusType.ACTIVATED.getKey());
		this.update(newUser, oldUser);
//		userDao.updateStatus(userId, UserStatusType.ACTIVATED.getKey());
		userActivateDao.del(code);
		return true;
	}

	@Override
	public User get(int userId) {
		return userDao.get(userId);
	}

	@Override
	public void checkUser(int userId) {
		User user = this.get(userId);
		if (user == null) {
			throw new RuntimeException("没有此用户[" + userId + "]");
		}
	}

	@Override
	public int count() {
		return userDao.count();
	}

	@Override
	public List<User> listByPosttime(int start, int size) {
		return userDao.listByPosttime(start, size);
	}

	@Override
	public boolean checkUsername(String username) {
		return userDao.getByUsername(username) == null;
	}

	@Override
	public boolean checkEmail(String email) {
		return userDao.getByEmail(email) == null;
	}

	@Override
	public User get(String username) {
		return userDao.getByUsername(username);
	}

	@Override
	public boolean update(User newUser, User oldUser) {
		boolean result = userDao.update(newUser);
		if (result) {
			activeUserDao.update(newUser, oldUser);
		}
		return result;
	}

	@Override
	public boolean updateHeadImg(int userId, String url) {
		User oldUser = this.get(userId);
		User newUser = this.copyUser(oldUser);
		newUser.setHeadImg(url);
		return this.update(newUser, oldUser);
//		return userDao.updateHeadImg(userId, url);
	}

	@Override
	public boolean updatePassword(int userId, String password) {
		User oldUser = this.get(userId);
		User newUser = this.copyUser(oldUser);
		newUser.setPassword(password);
		return this.update(newUser, oldUser);
//		return userDao.updatePassword(userId, password);
	}

	@Override
	public boolean incrVideo(int userId) {
		User oldUser = this.get(userId);
		User newUser = this.copyUser(oldUser);
		newUser.setVideoCount(oldUser.getVideoCount() + 1);
		return this.update(newUser, oldUser);
//		return userDao.incrVideo(userId);
	}

	@Override
	public List<User> listActiveUser(int start, int size) {
		List<User> list = activeUserDao.list(start, size);
		if (ListUtil.isEmpty(list)) {
			List<User> allList = userDao.listByPosttime(0, Integer.MAX_VALUE);
			for (User user : allList) {
				activeUserDao.add(user, Constants.DEFAULT_DATE);
			}
			list = activeUserDao.list(start, size);
		}
		return list;
	}

	@Override
	public String getHeadImg(int userId) {
		String headImg = userHeadImgDao.get(userId);
		if (StringUtils.isEmpty(headImg)) {
			User user = this.get(userId);
			userHeadImgDao.add(userId, user.getHeadImg());
			headImg = user.getHeadImg();
		}
		return headImg;
	}

	@Override
	public void checkAdmin(int userId) {
		User user = this.get(userId);
		if (user == null || user.getRole() != RoleType.ADMIN.getKey()) {
			throw new RuntimeException("不是管理员");
		}
	}

	@Override
	public boolean updateActiveTime(int userId) {
		User user = this.get(userId);
		Assert.notNull(user, "没有对应的用户[" + userId + "]");
		return activeUserDao.add(user, new Date());
	}

	@Override
	public boolean applyReset(String email) {
		String code = CodeUtil.generateString(50);
		User user = userDao.getByEmail(email);
		if (user == null) {
			return false;
		}
		userResetDao.add(user.getUserId(), code);
		MailInfo mailInfo = EmailUtil.getPasswordResetMailInfo(user.getEmail(), code);
		MailSender.sendHtmlMail(mailInfo);// 发送html格式
		return true;
	}

	@Override
	public boolean reset(String code, String password) {
		int userId = userResetDao.get(code);
		if (userId == 0) {
			throw new RuntimeException(ErrorTipsType.RESET_URL_ERROR.getDesc());
		}
		User oldUser = this.get(userId);
		User newUser = this.copyUser(oldUser);
		newUser.setPassword(password);
		this.update(newUser, oldUser);
		userResetDao.del(code);
		return true;
	}

}
