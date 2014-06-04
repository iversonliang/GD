package com.GD.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisPool {
	
	private JedisPool pool;
	
	public RedisPool(String host, int port, int timeout, int maxActive) {
		if (maxActive <= 0) {
			maxActive = 128;
		}
		if (timeout <= 0) {
			timeout = 10000;
		}

		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(maxActive);
		// poolConfig.setMinEvictableIdleTimeMillis(24 * 3600 * 1000);
		poolConfig.setMinEvictableIdleTimeMillis(-1);// ahai 20131024 已建立的连接不回收，高并发时建立连接会很耗资源
		// poolConfig.setTimeBetweenEvictionRunsMillis(-1);

		this.pool = new JedisPool(poolConfig, host, port, timeout);
	}
	
	public Jedis getResource() {
		return pool.getResource();
	}

	public void returnBrokenResource(Jedis jedis) {
		pool.returnBrokenResource(jedis);
	}

	public void returnResource(Jedis jedis) {
		pool.returnResource(jedis);
	}

	public void destroy() {
		pool.destroy();
	}
	
}
