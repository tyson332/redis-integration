package com.redis.intergation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redis.intergation.utility.ResponseUtility;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
@RequestMapping("HealthCheckController")
@PropertySource("classpath:application.properties")
public class HealthCheckController {

	@Value("${application.properties.test}")
	private String applicationPropertiesTest;

	@Autowired
	JedisPool jedisPool;

	@Autowired
	ResponseUtility responseUtility;

	@GetMapping(path = "/hello-controller")
	public ResponseEntity<String> springConnectivity() {
		return responseUtility.setResponse("World", HttpStatus.OK);
	}

	@GetMapping(path = "/hello-application-properties")
	public ResponseEntity<String> appProperties() {
		return responseUtility.setResponse(applicationPropertiesTest, HttpStatus.OK);
	}

	@GetMapping(path = "/jedis-ping")
	public ResponseEntity<String> jedisPing() {
		try (Jedis jedis = jedisPool.getResource()) {
		    String output = jedis.get("ping");
			return responseUtility.setResponse(output, HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
}
