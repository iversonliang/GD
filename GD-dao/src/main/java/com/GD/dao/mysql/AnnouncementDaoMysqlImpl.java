package com.GD.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.AnnouncementDao;
import com.GD.model.Announcement;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;

@Repository
public class AnnouncementDaoMysqlImpl implements AnnouncementDao {

	@Autowired
	private Jdbc jdbc;

	@Override
	public boolean add(Announcement announcement) {
		String sql = "INSERT INTO announcement(announcement_id,title,content,img_url,posttime) values(?,?,?,?,?)";
		StatementParameter param = new StatementParameter();
		param.setInt(announcement.getAnnouncementId());
		param.setString(announcement.getTitle());
		param.setString(announcement.getContent());
		param.setString(announcement.getImgUrl());
		param.setDate(announcement.getPosttime());
		return jdbc.insertForBoolean(sql, param);
	}

	@Override
	public int count() {
		String sql = "SELECT COUNT(*) FROM announcement";
		return jdbc.queryForInt(sql);
	}

	@Override
	public List<Announcement> list(int start, int size) {
		String sql = "SELECT * FROM announcement ORDER BY posttime DESC";
		return jdbc.queryForList(sql, Announcement.class, start, size);
	}

	@Override
	public boolean delete(int announcementId) {
		String sql = "DELETE FROM announcement WHERE announcement_id=?";
		return jdbc.updateForBoolean(sql, announcementId);
	}

}
