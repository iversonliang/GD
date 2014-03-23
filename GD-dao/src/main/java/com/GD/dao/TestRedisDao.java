package com.GD.dao;

public interface TestRedisDao {

	public String get(String key);
	
	public boolean set(String key, String value);
}
