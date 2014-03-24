package com.GD.dao.mysql;

import java.util.Date;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.UserDao;
import com.GD.model.User;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;

@Repository
public class UserDaoMysqlImpl implements UserDao {

	@Autowired
	private Jdbc jdbc;

	@Override
	public boolean add(User user) {
		String sql = "INSERT INTO user(username,password,nickname,email,question,answer,posttime) VALUES(?,?,?,?,?,?,?);";
		StatementParameter param = new StatementParameter();
		param.setString(user.getUsername());
		param.setString(user.getPassword());
		param.setString(StringUtils.defaultIfEmpty(user.getNickname(), ""));
		param.setString(StringUtils.defaultIfEmpty(user.getEmail(), ""));
		param.setString(StringUtils.defaultIfEmpty(user.getQuestion(), ""));
		param.setString(StringUtils.defaultIfEmpty(user.getAnswer(), ""));
		param.setDate(new Date());
		return jdbc.insertForBoolean(sql, param);
	}

	@Override
	public User get(String username, String password) {
		String sql = "SELECT * FROM user WHERE username=? AND password=?";
		return jdbc.query(sql, User.class, username, password);
	}

	@Override
	public boolean update(User user) {
		throw new NotImplementedException();
	}
}
