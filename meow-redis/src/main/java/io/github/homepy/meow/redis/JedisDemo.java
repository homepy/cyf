package io.github.homepy.meow.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {

	public static void main(String[] args) {
		// non thread-safe
		Jedis jedis = new Jedis("192.168.1.100", 6379);
		jedis.set("foo", "bar");
		System.out.println(jedis.get("foo"));
		
		jedis.close();

		JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.1.100");
		Jedis client = pool.getResource();

		jedis.set("foo", "bar");
		String foobar = jedis.get("foo");
		jedis.zadd("sose", 0, "car");
		jedis.zadd("sose", 0, "bike");
		Set<String> sose = jedis.zrange("sose", 0, -1);

		client.close();
		pool.close();

		System.out.println(foobar);
		System.out.println(sose);
	}
}
