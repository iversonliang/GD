package com.GD.dao;

import java.util.Date;
import java.util.List;

public interface TempImgDao {

	public boolean add(String imgType, String filename);
	
	public boolean remove(String imgType, String filename);
	
	public List<String> listBefore(String imgType, Date date);
}
