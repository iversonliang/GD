package com.GD.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.TestDao;
import com.GD.dao.TestRedisDao;
import com.GD.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestRedisDao testRedisDao;
	@Autowired
	private TestDao testDao;

	public void test() {
//		System.out.println("service");
//		testDao.count();
		
		System.out.println(testRedisDao.get("key"));
		testRedisDao.set("key1", "test1");
	}

}
