package com.GD.dao;

import java.util.List;

import com.GD.model.Ad;

public interface AdDao {

	public boolean add(Ad ad);
	
	public Ad get(int adId);
	
	public boolean updateIndexBetween(int adAreaType, int start, int end, boolean isIncr);
	
	public boolean updateAdAreaTypeIndex(int adId, int adAreaType, int indexNum);
	
	public Ad getByIndexNum(int adAreaType, int indexNum);
	
	public int getMaxIndexNum(int adAreaType);
	
	public boolean delete(int adId);
	
	public int count(int adAreaType);
	
	public List<Ad> list(int adAreaType, int start, int size);
}
