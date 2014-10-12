package com.GD.dao.mysql;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.VideoDao;
import com.GD.model.Video;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;
import com.GD.type.HomeType;
import com.GD.type.SortType;
import com.GD.type.TimeLimitType;
import com.GD.util.DateUtil;

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
	public int count(int status, int videoType, int homeType, int videoGradeType, int videoSourceType, int timeLimitType, String keyword, boolean showDel) {
		String sql = "SELECT COUNT(*) FROM video WHERE del=0";
		StatementParameter params = new StatementParameter();
		sql += this.getQuerySql(params, videoType, homeType, videoGradeType, videoSourceType, timeLimitType, keyword, status, showDel);
		return jdbc.queryForInt(sql, params);
	}

	@Override
	public List<Video> list(int status, int videoType, int homeType, int videoGradeType, int videoSourceType, int sortType, int timeLimitType, String keyword, boolean showDel, int start, int size) {
		String sql = "SELECT * FROM video WHERE 1=1 ";
		StatementParameter params = new StatementParameter();
		sql += this.getQuerySql(params, videoType, homeType, videoGradeType, videoSourceType, timeLimitType, keyword, status, showDel);
		if (homeType != HomeType.IGNORE.getKey()) {
			sql += " ORDER BY index_num";
			if (sortType != SortType.ALL.getKey()) {
				sql += "," + SortType.toType(sortType).toString().toLowerCase() + " DESC ";
			}
		} else {
			if (sortType != SortType.ALL.getKey()) {
				sql += " ORDER BY " + SortType.toType(sortType).toString().toLowerCase() + " DESC ";
			} else {
				sql += " ORDER BY posttime DESC";
			}
		}
		sql += " LIMIT ?,?";
		params.setInt(start);
		params.setInt(size);
		return jdbc.queryForList(sql, Video.class, params);
	}

	private String getQuerySql(StatementParameter params, int videoType, int homeType, int videoGradeType, int videoSourceType, int timeLimitType, String keyword, int status, boolean showDel) {
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
		if (timeLimitType != 0) {
			querySql += " AND posttime>? ";
			params.setDate(this.getTimeLimitDate(timeLimitType));
		}
		if (StringUtils.isNotEmpty(keyword)) {
			querySql += " AND (name LIKE ? or label LIKE ?)";
			params.setString("%" + keyword + "%");
			params.setString("%" + keyword + "%");
		}
		return querySql;
	}
	
	private Date getTimeLimitDate(int timeLimitType) {
		Date result = null;
		if (timeLimitType == TimeLimitType.MONTH.getKey()) {
			result = DateUtil.addDate(DateUtil.getOnlyDate(new Date()), -30);
		}
		if (timeLimitType == TimeLimitType.THREE_DAY.getKey()) {
			result = DateUtil.addDate(DateUtil.getOnlyDate(new Date()), -3);
		}
		if (timeLimitType == TimeLimitType.WEEK.getKey()) {
			result = DateUtil.addDate(DateUtil.getOnlyDate(new Date()), -7);
		}
		if (timeLimitType == TimeLimitType.YEAR.getKey()) {
			result = DateUtil.addDate(DateUtil.getOnlyDate(new Date()), -365);
		}
		return result;
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
	public List<Video> list(int userId, int videoSourceType, int start, int size) {
		String sql = "SELECT * FROM video WHERE user_id=?";
		StatementParameter param = new StatementParameter();
		param.setInt(userId);
		if (videoSourceType > 0) {
			sql += " AND video_source_type=?";
			param.setInt(videoSourceType);
		}
		sql += " AND del=0 ORDER BY posttime DESC LIMIT ?,?";
		param.setInt(start);
		param.setInt(size);
		return jdbc.queryForList(sql, Video.class, param);
	}

	@Override
	public int countByUser(int userId, int videoSourceType) {
		String sql = "SELECT COUNT(*) FROM video WHERE user_id=? AND del=0";
		StatementParameter param = new StatementParameter();
		param.setInt(userId);
		if (videoSourceType > 0) {
			sql += " AND video_source_type=?";
			param.setInt(videoSourceType);
		}
		return jdbc.queryForInt(sql, param);
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

	@Override
	public boolean updateVideoGradeType(int videoId, int videoGradeType) {
		String sql = "UPDATE video set video_grade_type=? WHERE video_id=?";
		StatementParameter params = new StatementParameter();
		params.setInt(videoGradeType);
		params.setInt(videoId);
		return jdbc.updateForBoolean(sql, params);
	}

	@Override
	public boolean removeAll() {
		throw new NotImplementedException();
	}

	@Override
	public boolean update(Video video) {
		String sql = "UPDATE video SET name=?,label=?,description=?,video_source_type=?,video_type=?,img_url=? WHERE video_id=?";
		StatementParameter params = new StatementParameter();
		params.setString(video.getName());
		params.setString(video.getLabel());
		params.setString(video.getDescription());
		params.setInt(video.getVideoSourceType());
		params.setInt(video.getVideoType());
		params.setString(video.getImgUrl());
		params.setInt(video.getVideoId());
		return jdbc.updateForBoolean(sql, params);
	}

	@Override
	public List<Video> listAllHomeType() {
		String sql = "SELECT * FROM video where home_type=1 and del=0 order by posttime desc";
		return jdbc.queryForList(sql, Video.class);
	}

	@Override
	public boolean updateHomeTypeIndex(int videoId, int index) {
		String sql = "update video set index_num=? where video_id=?";
		StatementParameter param = new StatementParameter();
		param.setInt(index);
		param.setInt(videoId);
		return jdbc.updateForBoolean(sql, index, videoId);
	}

	@Override
	public boolean updateNicknameByUser(int userId, String nickname) {
		String sql = "UPDATE video SET nickname=? WHERE user_id=?";
		return jdbc.updateForBoolean(sql, nickname, userId);
	}
	
}
