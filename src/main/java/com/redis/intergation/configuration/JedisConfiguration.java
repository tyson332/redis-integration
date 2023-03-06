package com.redis.intergation.configuration;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@PropertySource("classpath:application.properties")
public class JedisConfiguration {

	@Value("${redis.url}")
	private String redisURL;

	@Value("${redis.port}")
	private int redisPort;

	@Value("${redis.user}")
	private String redisUser;
	
	@Value("${redis.password}")
	private String redisPassword;

	@Value("${redis.read.timeout}")
	private int redisReadTimeout;

	@Value("${redis.timeout}")
	private int redisTimeout;

	
	/*
	 * @Bean(name="jedisInstance") public Jedis initializeJedis() { return new
	 * Jedis(redisURL); }
	 */
	
	@Bean(name="jedisPool")
	public JedisPool  getJedisPool() {
		JedisPoolConfig jedisPoolConfig = buildPoolConfig();
		JedisPool jedisPool4 = new JedisPool(jedisPoolConfig,redisURL,redisPort,redisUser,redisPassword);
		return jedisPool4;
	}
	
	private JedisPoolConfig buildPoolConfig() {
	    final JedisPoolConfig poolConfig = new JedisPoolConfig();
	    poolConfig.setMaxTotal(128);
	    poolConfig.setMaxIdle(128);
	    poolConfig.setMinIdle(16);
	    poolConfig.setTestOnBorrow(true);
	    poolConfig.setTestOnReturn(true);
	    poolConfig.setTestWhileIdle(true);
	    poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
	    poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
	    poolConfig.setNumTestsPerEvictionRun(3);
	    poolConfig.setBlockWhenExhausted(true);
	    return poolConfig;
	}
	
//	@Bean(name = "jedisInstance")
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(redisURL);
		redisStandaloneConfiguration.setPort(redisPort);
		redisStandaloneConfiguration.setDatabase(0);
		redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));

		JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
		jedisClientConfiguration.connectTimeout(Duration.ofSeconds(redisTimeout));
		jedisClientConfiguration.readTimeout(Duration.ofSeconds(redisReadTimeout));

		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
				jedisClientConfiguration.build());
		return jedisConFactory;
	}

//	final JedisPoolConfig poolConfig = buildPoolConfig();
//	JedisPool jedisPool = new JedisPool(poolConfig, redisURL,redisPort,"default",redisPassword);
//	JedisPool jedisPool = new JedisPool(buildPoolConfig(), buidJedisShardInfo());

	/*
	 * private JedisPoolConfig buildPoolConfig() { final JedisPoolConfig poolConfig
	 * = new JedisPoolConfig(); // poolConfig.set poolConfig.setMaxTotal(128);
	 * poolConfig.setMaxIdle(128); poolConfig.setMinIdle(16);
	 * poolConfig.setTestOnBorrow(true); poolConfig.setTestOnReturn(true);
	 * poolConfig.setTestWhileIdle(true);
	 * poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
	 * poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis()
	 * ); poolConfig.setNumTestsPerEvictionRun(3);
	 * poolConfig.setBlockWhenExhausted(true); return poolConfig; }
	 * 
	 * private JedisShardInfo buidJedisShardInfo() { JedisShardInfo jedisShardInfo =
	 * new JedisShardInfo();
	 * 
	 * }
	 */
}
