package com.redis.intergation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redis.intergation.beans.RedisString;
import com.redis.intergation.utility.ResponseUtility;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("redis-controller")
public class RedisController {

	@Autowired
	Jedis jedisInstance;

	@Autowired
	ResponseUtility responseUtility; 
	
	@PostMapping(path="/insertString",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertString(@RequestBody RedisString redisString) {
	    final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		jedisInstance.set(redisString.getKeyName(), redisString.getValue());
		Map<String,String> temp = responseUtility.getResponse("success");
		return responseUtility.setResponse("success", HttpStatus.OK);
	}
}

