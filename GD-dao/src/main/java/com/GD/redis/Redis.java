package com.GD.redis;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class Redis implements JedisCommands {
	
	protected Log logger = LogFactory.getLog(this.getClass());

	private RedisPool pool;
	
	private String host;
	private int port;
	private int timeout;
	private int maxActive;
	private int maxTotal;
	private int initialPoolSize;
	
	public int getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getInitialPoolSize() {
		return initialPoolSize;
	}

	public void setInitialPoolSize(int initialPoolSize) {
		this.initialPoolSize = initialPoolSize;
	}

	public Redis() {}
	
	public Redis(String host, int port, int timeout, int maxActive) {
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		this.maxActive = maxActive;
	}
	
	private interface Invoker {
		public Object doOperate(Jedis jedis);
	}
	
	@PostConstruct
	public void init() {
		// System.err.println("RedisImpl server:" + server);
		try {
			this.pool = new RedisPool(host, port, timeout, maxActive, maxTotal);
		}
		catch (RuntimeException e) {
			logger.error("host:" + host + " port:" + port + " timeout:" + timeout);
			throw e;
		}
		
		try {
			this.initPool();
		}
		catch (Exception e) {
			logger.error("初始化redis[" + this.host + "]连接数出错:" + e.getMessage());
		}
	}
	
	/**
	 * 初始化默认连接数量.
	 */
	protected void initPool() {
		// System.err.println("initPool server:" + this.server + " initialPoolSize:" + initialPoolSize + " start");
		if (this.initialPoolSize <= 0) {
			return;
		}
		int size;
		if (this.initialPoolSize > this.maxActive) {
			size = this.maxActive;
		}
		else {
			size = this.initialPoolSize;
		}

		Jedis[] jedisArr = new Jedis[size];
		for (int i = 0; i < jedisArr.length; i++) {
			jedisArr[i] = this.getResource();
		}

		// int numActive = pool.getInternalPool().getNumActive();

		for (int i = 0; i < jedisArr.length; i++) {
			this.pool.returnResource(jedisArr[i]);
		}
		// int numActive2 = pool.getInternalPool().getNumActive();
		// System.err.println("initPool server:" + this.server + " initialPoolSize:" + initialPoolSize + " numActive:" + numActive + " numActive2:" + numActive2 + " end");
	}
	
	public Jedis getResource() {
		long startTime = System.nanoTime();
		try {
			return this.pool.getResource();
		}
		catch (JedisConnectionException e) {
			String message = this.getErrorMessage(e);
			throw new JedisConnectionException(message, e);
			// throw e;
		}
		finally {
			long endTime = System.nanoTime();
			long time = (endTime - startTime) / 1000L / 1000L; // time 单位:毫秒
//			if (time >= 10) {
				// GenericObjectPool internalPool = pool.getInternalPool();
				String message = "get redis resource host:" + host + " port:" + port;
				message += " time:" + time;
				logger.info(message);
				// message += " maxActive:" + internalPool.getMaxActive();
				// message += " active:" + internalPool.getNumActive();
				// message += " idle:" + (internalPool.getMaxActive() - internalPool.getNumActive());

//			}
		}
	}
	
	/**
	 * 封装错误信息.
	 * 
	 * @param e
	 * @return
	 */
	protected String getErrorMessage(Exception e) {
		String message = "host:" + host + " port:" + port + " messsage:" + e.getMessage();
		logger.error(message);
		return message;
	}

	/**
	 * 执行jedis的操作.
	 * 
	 * @param invoker
	 *            调度接口
	 * @return
	 */
	protected Object execute(Invoker invoker) {
		Jedis jedis = this.getResource();
		try {
			return invoker.doOperate(jedis);
		}
		catch (JedisConnectionException e) {
			this.pool.returnBrokenResource(jedis);
			String message = this.getErrorMessage(e);
			// message += " key:" + key;
			throw new JedisConnectionException(message, e);
		}
		catch (RuntimeException e) {
			this.pool.returnBrokenResource(jedis);
			throw e;
		}
		catch (Exception e) {
			this.pool.returnBrokenResource(jedis);
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			this.pool.returnResource(jedis);
		}
	}
	
	@Override
	public String set(final String key, final String value) {
		return (String) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.set(key, value);
			}
		});
	}

	@Override
	public String get(final String key) {
		return (String) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.get(key);
			}
		});
	}

	@Override
	public Boolean exists(final String key) {
		return (Boolean) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	@Override
	public Long persist(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String type(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long expire(final String key, final int seconds) {
		return (Long) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.expire(key, seconds);
			}
		});
	}

	@Override
	public Long expireAt(String key, long unixTime) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long ttl(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Boolean setbit(String key, long offset, boolean value) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Boolean setbit(String key, long offset, String value) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Boolean getbit(String key, long offset) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long setrange(String key, long offset, String value) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String getSet(String key, String value) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long setnx(final String key, final String value) {
		return (Long) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.setnx(key, value);
			}
		});
	}

	@Override
	public String setex(String key, int seconds, String value) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long decrBy(String key, long integer) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long decr(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long incrBy(String key, long integer) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long incr(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long append(String key, String value) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String substr(String key, int start, int end) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long hset(final String key, final String field, final String value) {
		return (Long) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.hset(key, field, value);
			}
		});
	}

	@Override
	public String hget(final String key, final String field) {
		return (String) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.hget(key, field);
			}
		});
	}

	@Override
	public Long hsetnx(String key, String field, String value) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long hincrBy(String key, String field, long value) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Boolean hexists(String key, String field) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long hdel(String key, String... field) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long hlen(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<String> hkeys(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public List<String> hvals(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long rpush(String key, String... string) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long lpush(String key, String... string) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long llen(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String ltrim(String key, long start, long end) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String lindex(String key, long index) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String lset(String key, long index, String value) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long lrem(String key, long count, String value) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String lpop(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String rpop(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long sadd(final String key, final String... member) {
		return (Long) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.sadd(key, member);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> smembers(final String key) {
		return (Set<String>) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.smembers(key);
			}
		});
	}

	@Override
	public Long srem(String key, String... member) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String spop(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long scard(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Boolean sismember(String key, String member) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String srandmember(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long strlen(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long zadd(final String key, final double score, final String member) {
		return (Long) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.zadd(key, score, member);
			}
		});
	}

	@Override
	public Long zadd(final String key, final Map<String, Double> scoreMembers) {
		return (Long) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.zadd(key, scoreMembers);
			}
		});
	}

	@Override
	public Set<String> zrange(final String key, final long start, final long end) {
		return (Set<String>) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.zrange(key, start, end);
			}
		});
	}

	@Override
	public Long zrem(final String key, final String... member) {
		return (Long) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.zrem(key, member);
			}
		});
	}

	@Override
	public Double zincrby(String key, double score, String member) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long zrank(final String key, final String member) {
		return (Long) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.zrank(key, member);
			}
		});
	}

	@Override
	public Long zrevrank(String key, String member) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> zrevrange(final String key, final long start, final long end) {
		return (Set<String>) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.zrevrange(key, start, end);
			}
		});
	}

	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long end) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long zcard(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Double zscore(String key, String member) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public List<String> sort(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public List<String> sort(String key, SortingParams sortingParameters) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long zcount(String key, double min, double max) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long zcount(String key, String min, String max) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<String> zrangeByScore(final String key, final double min, final double max) {
		return (Set<String>) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.zrangeByScore(key, min, max);
			}
		});
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final double max, final double min) {
		return (Set<String>) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.zrevrangeByScore(key, min, max);
			}
		});
	}

	@Override
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<String> zrevrangeByScore(final String key, final String max, final String min) {
		return (Set<String>) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.zrevrangeByScore(key, max, min);
			}
		});
	}

	@Override
	public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long zremrangeByRank(String key, long start, long end) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long zremrangeByScore(String key, String start, String end) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long lpushx(String key, String... string) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long rpushx(String key, String... string) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public List<String> blpop(String arg) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public List<String> brpop(String arg) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long del(final String key) {
		return (Long) this.execute(new Invoker() {
			@Override
			public Object doOperate(Jedis jedis) {
				return jedis.del(key);
			}
		});
	}

	@Override
	public String echo(String string) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long move(String key, int dbIndex) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long bitcount(String key) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long bitcount(String key, long start, long end) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, int cursor) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public ScanResult<String> sscan(String key, int cursor) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public ScanResult<Tuple> zscan(String key, int cursor) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public ScanResult<String> sscan(String key, String cursor) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public ScanResult<Tuple> zscan(String key, String cursor) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public Long pfadd(String arg0, String... arg1) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public long pfcount(String arg0) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	@Override
	public String set(String arg0, String arg1, String arg2, String arg3, long arg4) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}
}
