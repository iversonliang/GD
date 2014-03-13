package com.GD.dao.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.GD.dao.TestDao;
import com.GD.model.UserClock;

@Repository
public class TestDaoMysqlImpl implements TestDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int count() {
		System.out.println("dao");
		String sql = "select count(*) from user_clock";
		int result = this.jdbcTemplate.queryForInt(sql);
		System.out.println(result);
		this.get(50004297);
		return result;
	}

	@Override
	public UserClock get(int id) {
		String sql = "select * from user_clock where yyuid=?";
		UserClock clock = this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(UserClock.class), id);
		System.out.println(clock.getServerName());
		return null;
	}

}
