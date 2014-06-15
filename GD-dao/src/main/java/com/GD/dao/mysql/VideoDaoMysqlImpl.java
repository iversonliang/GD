package com.GD.dao.mysql;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.VideoDao;
import com.GD.model.Video;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;

@Repository
public class VideoDaoMysqlImpl implements VideoDao {
	
	@Autowired
	private Jdbc jdbc;

	@Override
	public boolean add(Video video) {
		String sql = "INSERT INTO video(name,description,url,play,comments,love,user_id,nickname,posttime,del,status,video_type,video_grade_type,label,video_source_type) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		StatementParameter params = new StatementParameter();
		params.setString(video.getName());
		params.setString(video.getDescription());
		params.setString(video.getUrl());
		params.setInt(video.getPlay());
		params.setInt(video.getComments());
		params.setInt(video.getLove());
		params.setInt(video.getUserId());
		params.setString(video.getNickname());
		params.setDate(video.getPosttime());
		params.setBool(video.isDel());
		params.setInt(video.getStatus());
		params.setInt(video.getVideoType());
		params.setInt(video.getVideoGradeType());
		params.setString(video.getLabel());
		params.setInt(video.getVideoSourceType());
		return jdbc.insertForBoolean(sql, params);
	}

	@Override
	public Video get(int videoId) {
		String sql = "SELECT * FROM video WHERE video_id=?";
		return jdbc.query(sql, Video.class, videoId);
	}

	@Override
	public int count(int status, int videoType, int videoGradeType, String name, String label) {
		String sql = "SELECT COUNT(*) FROM video WHERE 1=1";
		StatementParameter params = new StatementParameter();
		sql += this.getQuerySql(params, sql, videoType, videoGradeType, name, label, status);
		return jdbc.queryForInt(sql, params);
	}

	@Override
	public List<Video> list(int status, int videoType, int videoGradeType, String name, String label, int start, int size) {
		String sql = "SELECT * FROM video WHERE 1=1";
		StatementParameter params = new StatementParameter();
		sql += this.getQuerySql(params, sql, videoType, videoGradeType, name, label, status);
		sql += " LIMIT ?,?";
		params.setInt(start);
		params.setInt(size);
		return null;
	}

	private String getQuerySql(StatementParameter params, String sql, int videoType, int videoGradeType, String name, String label, int status) {
		if (status != 0) {
			sql += " AND status=?";
			params.setInt(status);
		}
		if (videoType != 0) {
			sql += " AND video_type=?";
			params.setInt(videoType);
		}
		if (videoGradeType != 0) {
			sql += " AND video_grade_type=?";
			params.setInt(videoGradeType);
		}
		if (StringUtils.isNotEmpty(name)) {
			sql += " AND name LIKE ?";
			params.setString("%" + name + "%");
		}
		if (StringUtils.isNotEmpty(label)) {
			sql += " AND label LIKE ?";
			params.setString("%" + label + "%");
		}
		return sql;
	}

	@Override
	public boolean play(int videoId) {
		String sql = "UPDATE video SET play=play+1 WHERE video_id=?";
		return jdbc.updateForBoolean(sql, videoId);
	}

	@Override
	public boolean love(int videoId) {
		String sql = "UPDATE video SET love=love+1 WHERE video_id=?";
		return jdbc.updateForBoolean(sql, videoId);
	}

	@Override
	public boolean commont(int videoId) {
		String sql = "UPDATE video SET commont=commont+1 WHERE video_id=?";
		return jdbc.updateForBoolean(sql, videoId);
	}

	@Override
	public boolean del(int videoId) {
		String sql = "UPDATE video SET del=1 WHERE video_id=?";
		return jdbc.updateForBoolean(sql, videoId);
	}
	
}
