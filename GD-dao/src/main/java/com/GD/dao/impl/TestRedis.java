package com.GD.dao.impl;

import redis.clients.jedis.Jedis;

public class TestRedis {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.229.128");
		System.out.println(jedis.get("key"));
	}
}
