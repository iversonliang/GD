package com.GD.service;

import java.util.List;

import com.GD.model.Ad;
import com.GD.type.AdAreaType;

public interface AdService {
	
	public boolean add(int userId, Ad ad);
	
	public boolean delete(int adId);
	
	public boolean updateIndex(int adId, AdAreaType adAreaType, int indexNum);
	
	public int count(AdAreaType type);
	
	public List<Ad> list(AdAreaType type, int start, int size);

}
