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
		String sql = "INSERT INTO like_video(like_video_id,video_id,user_id) VALUES(?,?,?)";
		StatementParameter param = new StatementParameter();
		param.setInt(likeVideo.getLikeVideoId());
		param.setInt(likeVideo.getVideoId());
		param.setInt(likeVideo.getUserId());
		return jdbc.insertForBoolean(sql, param);
	}

	@Override
	public List<LikeVideo> listByUser(int userId) {
		String sql = "SELECT * FROM like_video WHERE user_id=?";
		return jdbc.queryForList(sql, LikeVideo.class, userId);
	}

}
