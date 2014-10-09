package com.GD.redis;

import com.GD.util.DateUtil;
import com.GD.util.RedisKey;

import redis.clients.jedis.Jedis;

public class Test {

	public static void main(String[] args) {
//		Redis reids = new Redis("192.168.1.128", 6379, 10000, 10);
//		reids.init();
//		Jedis jedis = reids.getResource();
//		System.out.println(jedis.get("key"));
//		System.out.println("done");
//		
//		
//		 Jedis jedis = new Jedis("192.168.1.128");
//		 String key = RedisKey.getActiveUser();
//		 double score = DateUtil.getShortSeconds();
//		 String member = "{\"userId\":15,\"username\":\"test1123\",\"password\":\"123abc\",\"nickname\":\"\",\"email\":\"123@12.com\",\"question\":\"\",\"answer\":\"\",\"status\":0,\"sex\":1,\"role\":0,\"posttime\":1411841321000,\"city\":\"万州\",\"province\":\"重庆\",\"realName\":\"\",\"birthday\":-28800000,\"danceType\":\"\",\"description\":\"\",\"sign\":\"\",\"headImg\":\"/images/defaultHead.jpg\",\"videoCount\":15,\"groupType\":1}";
////		 jedis.zadd(key, score, member);
//		 System.out.println(jedis.zrangeWithScores(key, 0, -1));
//		 
//		 System.out.println(jedis.zrank(key, member));
	}
}
