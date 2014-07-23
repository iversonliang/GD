package com.GD.dao.mysql;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.VideoDao;
import com.GD.model.Video;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;
import com.GD.type.HomeType;

@Repository
public class VideoDaoMysqlImpl implements VideoDao {
	
	@Autowired
	private Jdbc jdbc;

	@Override
	public boolean add(Video video) {
		String sql = "INSERT INTO video(name,img_url,play_url,source_site_type,description,url,play,comments,love,user_id,nickname,posttime,del,status,video_type,video_grade_type,label,video_source_type,home_type,index_num) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		StatementParameter params = new StatementParameter();
		params.setString(video.getName());
		params.setString(video.getImgUrl());
		params.setString(video.getPlayUrl());
		params.setInt(video.getSourceSiteType());
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
		params.setInt(video.getHomeType());
		params.setInt(video.getIndexNum());
		return jdbc.insertForBoolean(sql, params);
	}

	@Override
	public Video get(int videoId) {
		String sql = "SELECT * FROM video WHERE video_id=?";
		return jdbc.query(sql, Video.class, videoId);
	}

	@Override
	public int count(int status, int videoType, int homeType, int videoGradeType, int videoSourceType, boolean showDel, String name, String label) {
		String sql = "SELECT COUNT(*) FROM video WHERE del=0";
		StatementParameter params = new StatementParameter();
		sql += this.getQuerySql(params, videoType, homeType, videoGradeType, videoSourceType, name, label, status, showDel);
		return jdbc.queryForInt(sql, params);
	}

	@Override
	public List<Video> list(int status, int videoType, int homeType, int videoGradeType, int videoSourceType, boolean showDel, String name, String label, int start, int size) {
		String sql = "SELECT * FROM video WHERE 1=1";
		StatementParameter params = new StatementParameter();
		sql += this.getQuerySql(params, videoType, homeType, videoGradeType, videoSourceType, name, label, status, showDel);
		if (homeType != HomeType.IGNORE.getKey()) {
			sql += " ORDER BY index_num LIMIT ?,?";
		} else {
			sql += " ORDER BY posttime DESC LIMIT ?,?";
		}
		params.setInt(start);
		params.setInt(size);
		return jdbc.queryForList(sql, Video.class, params);
	}

	private String getQuerySql(StatementParameter params, int videoType, int homeType, int videoGradeType, int videoSourceType, String name, String label, int status, boolean showDel) {
		String querySql = "";
		if (!showDel) {
			querySql += " AND del=0";
		}
		if (status != 0) {
			querySql += " AND status=?";
			params.setInt(status);
		}
		if (videoType != 0) {
			querySql += " AND video_type=?";
			params.setInt(videoType);
		}
		if (homeType != HomeType.IGNORE.getKey()) {
			querySql += " AND home_type=?";
			params.setInt(homeType);
		}
		if (videoGradeType != 0) {
			querySql += " AND video_grade_type=?";
			params.setInt(videoGradeType);
		}
		if (videoSourceType != 0) {
			querySql += " AND video_source_type=?";
			params.setInt(videoSourceType);
		}
		if (StringUtils.isNotEmpty(name)) {
			querySql += " AND name LIKE ?";
			params.setString("%" + name + "%");
		}
		if (StringUtils.isNotEmpty(label)) {
			querySql += " AND label LIKE ?";
			params.setString("%" + label + "%");
		}
		return querySql;
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
		String sql = "UPDATE video SET comments=comments+1 WHERE video_id=?";
		return jdbc.updateForBoolean(sql, videoId);
	}

	@Override
	public boolean del(int videoId) {
		String sql = "UPDATE video SET del=1, home_type=0, index_num=0 WHERE video_id=?";
		return jdbc.updateForBoolean(sql, videoId);
	}

	@Override
	public List<Video> list(int userId, int start, int size) {
		String sql = "SELECT * FROM video WHERE user_id=? AND del=0 ORDER BY play DESC LIMIT ?,?";
		return jdbc.queryForList(sql, Video.class, userId, start, size);
	}

	@Override
	public int countByUser(int userId) {
		String sql = "SELECT COUNT(*) FROM video WHERE user_id=? AND del=0";
		return jdbc.queryForInt(sql, userId);
	}

	@Override
	public int getMaxIndexNum(int homeType) {
		String sql = "SELECT MAX(index_num) FROM video WHERE home_type=?";
		return jdbc.queryForInt(sql, homeType);
	}

	@Override
	public Video getByIndexNum(int homeType, int indexNum) {
		String sql = "SELECT * FROM video WHERE index_num=? AND home_type=?";
		StatementParameter param = new StatementParameter();
		param.setInt(indexNum);
		param.setInt(homeType);
		return jdbc.query(sql, Video.class, param);
	}

	@Override
	public boolean updateIndexBetween(int homeType, int start, int end, boolean isIncr) {
		int num = isIncr ? 1 : -1;
		String desc = "";
		if (isIncr) {
			desc = " DESC";
		}
		String sql = "UPDATE video SET index_num = index_num + ? WHERE home_type=? AND index_num BETWEEN ? AND ? ORDER BY index_num " + desc;
		return jdbc.updateForBoolean(sql, num, homeType, start, end);
	}

	@Override
	public boolean updateHomeTypeIndex(int videoId, int homeType, int indexNum) {
		String sql = "UPDATE video SET home_type=?, index_num=? WHERE video_id=?";
		return jdbc.updateForBoolean(sql, homeType, indexNum, videoId);
	}

	@Override
	public boolean unDel(int videoId) {
		String sql = "UPDATE video SET del=0 WHERE video_id=?";
		return jdbc.updateForBoolean(sql, videoId);
	}
	
}
