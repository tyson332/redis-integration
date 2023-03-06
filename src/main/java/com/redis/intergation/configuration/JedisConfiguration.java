package com.redis.intergation.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import redis.clients.jedis.Jedis;

@Configuration
@PropertySource("classpath:application.properties")
public class JedisConfiguration {

	  @Value("${redisURL}")
	  private String redisURL;
	  
	@Bean(name="jedisInstance")
	public Jedis initializeJedis() {
		return new Jedis(redisURL);
	}
}
