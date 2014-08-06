package com.GD.dao.mysql;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.InviteCodeDao;
import com.GD.model.InviteCode;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;

@Repository
public class InviteCodeDaoMysqlImpl implements InviteCodeDao {
	
	@Autowired
	private Jdbc jdbc;

	@Override
	public boolean add(InviteCode inviteCode) {
		String sql = "INSERT INTO invite_code(invite_code_id,use_user_id,posttime,use_time) values(?,?,?,?)";
		StatementParameter param = new StatementParameter();
		param.setString(inviteCode.getInviteCodeId());
		param.setInt(inviteCode.getUseUserId());
		param.setDate(inviteCode.getPosttime());
		param.setDate(inviteCode.getUseTime());
		
		return jdbc.insertForBoolean(sql, param);
	}

	@Override
	public InviteCode get(String inviteCodeId) {
		String sql = "SELECT * FROM invite_code WHERE invite_code_id=?";
		return jdbc.query(sql, InviteCode.class);
	}

	@Override
	public int count() {
		String sql = "SELECT COUNT(*) FROM invite_code";
		return jdbc.queryForInt(sql);
	}

	@Override
	public List<InviteCode> list(int start, int size) {
		String sql = "SELECT * FROM invite_code";
		return jdbc.queryForList(sql, InviteCode.class, start, size);
	}

	@Override
	public boolean use(int userId, String inviteCodeId) {
		String sql = "UPDATE invite_code SET use_user_id=?,use_time=? WHERE invite_code_id=?";
		return jdbc.updateForBoolean(sql, userId, new Date(), inviteCodeId);
	}

}
