package com.GD.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.AdDao;
import com.GD.model.Ad;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;
import com.GD.type.AdAreaType;

@Repository
public class AdDaoMysqlImpl implements AdDao {
	
	@Autowired
	private Jdbc jdbc;

	@Override
	public boolean add(Ad ad) {
		String sql = "INSERT INTO ad(ad_id,ad_area_type,index_num,url,img_url,posttime,del) values(?,?,?,?,?,?,?)";
		StatementParameter param = new StatementParameter();
		param.setInt(ad.getAdId());
		param.setInt(ad.getAdAreaType());
		param.setInt(ad.getIndexNum());
		param.setString(ad.getUrl());
		param.setString(ad.getImgUrl());
		param.setDate(ad.getPosttime());
		param.setBool(ad.isDel());
		return jdbc.insertForBoolean(sql, param);
	}

	@Override
	public boolean delete(int adId) {
		String sql = "DELETE FROM ad WHERE ad_id=?";
		return jdbc.updateForBoolean(sql, adId);
	}

	@Override
	public boolean updateIndexBetween(int adAreaType, int start, int end, boolean isIncr) {
		int num = isIncr ? 1 : -1;
		String desc = "";
		if (isIncr) {
			desc = " DESC";
		}
		String sql = "UPDATE ad SET index_num = index_num + ? WHERE ad_area_type=? AND index_num BETWEEN ? AND ? ORDER BY index_num " + desc;
		return jdbc.updateForBoolean(sql, num, adAreaType, start, end);
	}

	@Override
	public int getMaxIndexNum(int adAreaType) {
		String sql = "SELECT MAX(index_num) FROM ad WHERE ad_area_type=?";
		return jdbc.queryForInt(sql, adAreaType);
	}

	@Override
	public boolean updateAdAreaTypeIndex(int adId, int adAreaType, int indexNum) {
		String sql = "UPDATE ad SET ad_area_type=?, index_num=? WHERE ad_id=?";
		return jdbc.updateForBoolean(sql, adAreaType, indexNum, adId);
	}

	@Override
	public Ad get(int adId) {
		String sql = "SELECT * FROM ad WHERE ad_id=?";
		return jdbc.query(sql, Ad.class, adId);
	}

	@Override
	public Ad getByIndexNum(int adAreaType, int indexNum) {
		String sql = "SELECT * FROM ad WHERE index_num=? AND ad_area_type=?";
		StatementParameter param = new StatementParameter();
		param.setInt(indexNum);
		param.setInt(adAreaType);
		return jdbc.query(sql, Ad.class, param);
	}

	@Override
	public int count(int adAreaType) {
		String sql = "SELECT COUNT(*) FROM ad WHERE 1=1";
		StatementParameter params = new StatementParameter();
		if (adAreaType != AdAreaType.IGNORE.getKey()) {
			sql += " AND ad_area_type=?";
			params.setInt(adAreaType);
		}
		return jdbc.queryForInt(sql, params);
	}

	@Override
	public List<Ad> list(int adAreaType, int start, int size) {
		String sql = "SELECT * FROM ad WHERE 1=1";
		StatementParameter params = new StatementParameter();
		if (adAreaType != AdAreaType.IGNORE.getKey()) {
			sql += " AND ad_area_type=?";
			params.setInt(adAreaType);
		}
		sql += " LIMIT ?,?";
		params.setInt(start);
		params.setInt(size);
		return jdbc.queryForList(sql, Ad.class, params);
	}

}
