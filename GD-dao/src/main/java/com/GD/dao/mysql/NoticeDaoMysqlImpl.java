package com.GD.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.NoticeDao;
import com.GD.model.Notice;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;

@Repository
public class NoticeDaoMysqlImpl implements NoticeDao{

	@Autowired
	private Jdbc jdbc;

	@Override
	public boolean add(Notice notice) {
		String sql = "INSERT INTO notice(notice_id,user_id,img_url,comment_id,video_id,content,posttime,op_user_id) values(?,?,?,?,?,?,?,?)";
		StatementParameter param = new StatementParameter();
		param.setInt(notice.getNoticeId());
		param.setInt(notice.getUserId());
		param.setString(notice.getImgUrl());
		param.setInt(notice.getCommentId());
		param.setInt(notice.getVideoId());
		param.setString(notice.getContent());
		param.setDate(notice.getPosttime());
		param.setInt(notice.getOpUserId());
		return jdbc.insertForBoolean(sql, param);
	}

	@Override
	public int count(int userId) {
		String sql = "SELECT COUNT(*) FROM notice WHERE user_id=?";
		return jdbc.queryForInt(sql, userId);
	}

	@Override
	public List<Notice> list(int userId, int start, int size) {
		String sql = "SELECT * FROM notice WHERE user_id=? ORDER BY posttime DESC LIMIT ?,?";
		return jdbc.queryForList(sql, Notice.class, userId, start, size);
	}
}
