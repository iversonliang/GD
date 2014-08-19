package com.GD.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.LikeVideoDao;
import com.GD.model.LikeVideo;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;

@Repository
public class LikeVideoDaoMysqlImpl implements LikeVideoDao{
	
	@Autowired
	private Jdbc jdbc;

	@Override
	public boolean add(LikeVideo likeVideo) {
		String sql = "INSERT IGNORE INTO like_video(like_video_id,video_id,user_id) VALUES(?,?,?)";
		StatementParameter param = new StatementParameter();
		param.setInt(likeVideo.getLikeVideoId());
		param.setInt(likeVideo.getVideoId());
		param.setInt(likeVideo.getUserId());
		return jdbc.insertForBoolean(sql, param);
	}

	@Override
	public List<LikeVideo> listByUser(int userId, int start, int size) {
		String sql = "SELECT * FROM like_video WHERE user_id=? LIMIT ?,?";
		return jdbc.queryForList(sql, LikeVideo.class, userId, start, size);
	}

	@Override
	public int count(int userId) {
		String sql = "SELECT COUNT(*) FROM like_video WHERE user_id=?";
		return jdbc.queryForInt(sql, userId);
	}

	@Override
	public boolean isLike(int userId, int videoId) {
		String sql = "SELECT * FROM like_video_id WHERE video_id=? AND user_id=?";
		StatementParameter param = new StatementParameter();
		param.setInt(videoId);
		param.setInt(userId);
		return jdbc.query(sql, LikeVideo.class, param) != null;
	}

	@Override
	public boolean delete(int userId, int videoId) {
		String sql = "DELETE FROM like_video WHERE video_id=? AND user_id=?";
		StatementParameter param = new StatementParameter();
		param.setInt(videoId);
		param.setInt(userId);
		return jdbc.updateForBoolean(sql, param);
	}

}
