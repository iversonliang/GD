package com.GD.dao.mysql;

import java.util.Date;
import java.util.List;

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
	public long add(User user) {
		String sql = "INSERT INTO user(username,password,nickname,email,question,answer,posttime,status,sex,role,city,province,real_name,birthday,dance_type,description,sign) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		StatementParameter param = new StatementParameter();
		param.setString(user.getUsername());
		param.setString(user.getPassword());
		param.setString(StringUtils.defaultIfEmpty(user.getNickname(), ""));
		param.setString(StringUtils.defaultIfEmpty(user.getEmail(), ""));
		param.setString(StringUtils.defaultIfEmpty(user.getQuestion(), ""));
		param.setString(StringUtils.defaultIfEmpty(user.getAnswer(), ""));
		param.setDate(new Date());
		param.setInt(user.getStatus());
		param.setInt(user.getSex());
		param.setInt(user.getRole());
		param.setString(StringUtils.defaultIfEmpty(user.getCity(), ""));
		param.setString(StringUtils.defaultIfEmpty(user.getProvince(), ""));
		param.setString(StringUtils.defaultIfEmpty(user.getRealName(), ""));
		param.setDate(user.getBirthday());
		param.setString(StringUtils.defaultIfEmpty(user.getDanceType(), ""));
		param.setString(StringUtils.defaultIfEmpty(user.getDescription(), ""));
		param.setString(StringUtils.defaultIfEmpty(user.getSign(), ""));
		return jdbc.insertForLastId(sql, param);
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

	@Override
	public boolean updateStatus(int userId, int status) {
		String sql = "UPDATE user set status=? WHERE user_id=?";
		return jdbc.updateForBoolean(sql, status, userId);
	}

	@Override
	public User get(int userId) {
		String sql = "SELECT * FROM user WHERE user_id=?";
		return jdbc.query(sql, User.class, userId);
	}

	@Override
	public int count() {
		String sql = "SELECT COUNT(*) FROM user";
		return jdbc.queryForInt(sql);
	}

	@Override
	public List<User> list(int start, int size) {
		String sql = "SELECT * FROM user";
		return jdbc.queryForList(sql, User.class, start, size);
	}

}
