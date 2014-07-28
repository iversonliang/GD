package com.GD.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.TempImgDao;
import com.GD.service.TempImgService;

@Service
public class TempImgServiceImpl implements TempImgService {

	@Autowired
	private TempImgDao tempImgDao;
	
	@Override
	public boolean add(String imgType, String filename) {
		return tempImgDao.add(imgType, filename);
	}

}
