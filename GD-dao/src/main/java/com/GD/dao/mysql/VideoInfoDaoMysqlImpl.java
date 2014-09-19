package com.GD.dao.mysql;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.VideoInfoDao;
import com.GD.model.VideoInfo;
import com.GD.model.WPPosts;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;

@Repository
public class VideoInfoDaoMysqlImpl implements VideoInfoDao {

	@Autowired
	private Jdbc jdbc;

	@Override
	public List<WPPosts> listWPPosts() {
		String sql = "SELECT * FROM wp_posts WHERE wp_posts.`post_status`='publish' AND post_type='post'";
		return jdbc.queryForList(sql, WPPosts.class);
	}

	@Override
	public List<String> listLabel(int id) {
		String sql = "SELECT wp_terms.`name` FROM wp_posts,wp_terms,wp_term_relationships,wp_term_taxonomy WHERE wp_posts.`ID`=? AND wp_posts.`ID` = wp_term_relationships.`object_id` AND wp_term_relationships.`term_taxonomy_id` = wp_term_taxonomy.`term_taxonomy_id` AND wp_term_taxonomy.`term_id` = wp_terms.`term_id`";
		StatementParameter param = new StatementParameter();
		param.setInt(id);
		return jdbc.queryForStrings(sql, param);
	}

	@Override
	public boolean add(VideoInfo videoInfo) {
		String sql = "INSERT into video_info(id,youku_id,post_name,post_date,post_title,label) values(?,?,?,?,?,?)";
		StatementParameter param = new StatementParameter();
		param.setInt(videoInfo.getId());
		param.setString(StringUtils.defaultIfEmpty(videoInfo.getYoukuId(), ""));
		param.setString(videoInfo.getPostName());
		param.setDate(videoInfo.getPostDate());
		param.setString(videoInfo.getPostTitle());
		param.setString(videoInfo.getLabel());
		return jdbc.insertForBoolean(sql, param);
	}

	@Override
	public VideoInfo get(int id) {
		String sql = "SELECT * FROM video_info where id=?";
		StatementParameter param = new StatementParameter();
		param.setInt(id);
		return jdbc.query(sql, VideoInfo.class, param);
	}

	@Override
	public List<VideoInfo> listVideoInfo() {
		String sql = "SELECT * FROM video_info";
		return jdbc.queryForList(sql, VideoInfo.class);
	}
}
