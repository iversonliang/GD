package com.GD.service.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.UserActivateDao;
import com.GD.dao.UserDao;
import com.GD.email.MailInfo;
import com.GD.email.MailSender;
import com.GD.model.User;
import com.GD.service.UserService;
import com.GD.type.UserStatusType;
import com.GD.util.CodeUtil;
import com.GD.util.EmailUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserActivateDao userActivateDao;
	@Autowired
	private UserDao userDao;

	@Override
	public boolean add(User user) {
		long result = userDao.add(user);

		if (result > 0) {

			String code = CodeUtil.generateString(50);
			userActivateDao.add((int)result, code);
			MailInfo mailInfo = EmailUtil.getMailInfo(user.getEmail(), code);
			
			try {
				MailSender.sendTextMail(mailInfo);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result > 0;
	}

	@Override
	public User get(String username, String password) {
		return userDao.get(username, password);
	}

	@Override
	public boolean updateStatus(int userId, UserStatusType type) {
		return userDao.updateStatus(userId, type.getKey());
	}

	@Override
	public boolean activate(String code) {
		int userId = userActivateDao.get(code);
		if (userId == 0) {
			return false;
		}
		userDao.updateStatus(userId, UserStatusType.ACTIVATED.getKey());
		userActivateDao.del(code);
		return true;
	}

	@Override
	public boolean waitActivate(int userId, String code) {
		return userActivateDao.add(userId, code);
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

}
