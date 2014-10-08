package com.GD.dao.mysql;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.ApplyDao;
import com.GD.model.Apply;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;

@Repository
public class ApplyDaoMysqlImpl implements ApplyDao {

	@Autowired
	private Jdbc jdbc;

	@Override
	public boolean add(Apply apply) {
		String sql = "INSERT INTO apply(apply_id,name,email,location,crew,oups_url,posttime,pass,user_id,invite_code_id) values(?,?,?,?,?,?,?,?,?,?)";
		StatementParameter param = new StatementParameter();
		param.setInt(apply.getApplyId());
		param.setString(StringUtils.defaultIfEmpty(apply.getName(), ""));
		param.setString(StringUtils.defaultIfEmpty(apply.getEmail(), ""));
		param.setString(StringUtils.defaultIfEmpty(apply.getLocation(), ""));
		param.setString(StringUtils.defaultIfEmpty(apply.getCrew(), ""));
		param.setString(StringUtils.defaultIfEmpty(apply.getOupsUrl(), ""));
		param.setDate(apply.getPosttime());
		param.setBool(apply.isPass());
		param.setInt(apply.getUserId());
		param.setString(apply.getInviteCodeId());
		return jdbc.insertForBoolean(sql, param);
	}

	@Override
	public boolean pass(int applyId, String inviteCodeId) {
		String sql = "UPDATE apply SET invite_code_id=? WHERE apply_id=?";
		return jdbc.updateForBoolean(sql, inviteCodeId, applyId);
	}

	@Override
	public int count() {
		String sql = "SELECT COUNT(*) FROM apply";
		return jdbc.queryForInt(sql);
	}

	@Override
	public List<Apply> list(int start, int size) {
		String sql = "SELECT * FROM apply ORDER BY posttime desc";
		return jdbc.queryForList(sql, Apply.class, start, size);
	}

	@Override
	public Apply getByUserId(int userId) {
		String sql = "SELECT * FROM apply WHERE user_id=?";
		return jdbc.query(sql, Apply.class, userId);
	}

	@Override
	public boolean activate(int userId) {
		String sql = "UPDATE apply SET pass=1 WHERE user_id=?";
		return jdbc.updateForBoolean(sql, userId);
	}

	@Override
	public Apply get(int applyId) {
		String sql = "SELECT * FROM apply where apply_id=?";
		return jdbc.query(sql, Apply.class, applyId);
	}
	
}
