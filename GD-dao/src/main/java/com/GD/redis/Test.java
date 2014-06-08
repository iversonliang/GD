package com.GD.redis;

import redis.clients.jedis.Jedis;

public class Test {

	public static void main(String[] args) {
		Redis reids = new Redis("192.168.229.128", 6379, 10000, 10);
		reids.init();
		Jedis jedis = reids.getResource();
		System.out.println(jedis.get("key"));
	}
}
