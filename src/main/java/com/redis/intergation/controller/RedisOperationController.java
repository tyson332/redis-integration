package com.redis.intergation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.redis.intergation.beans.RedisQueue;
import com.redis.intergation.beans.RedisString;
import com.redis.intergation.utility.ResponseUtility;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Tyson
 * Commands : https://redis.io/commands/
 * JedisJavaDocs : https://javadoc.io/doc/redis.clients/jedis/latest/index.html
 */

@Controller
@RequestMapping("redis-basic-operation-controller")
public class RedisOperationController {

	@Autowired
	JedisPool jedisPool;

	@Autowired
	ResponseUtility responseUtility;
	
	final HttpHeaders httpHeaders;
	
	RedisOperationController(){
	    httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	}
	
	@PostMapping(path="/insertString",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insertString(@RequestBody RedisString redisString) {
		try (Jedis jedis = jedisPool.getResource()) {
		    String output = jedis.set(redisString.getKeyName(), redisString.getValue());
			return responseUtility.setResponse(output, HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	
	@PostMapping(path="/pushToHeadOfQueue",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> pushToHeadOfQueue(@RequestBody RedisQueue redisQueue) {
		try (Jedis jedis = jedisPool.getResource()) {
			Long output = jedis.lpush(redisQueue.getQueueName(),redisQueue.getQueueValue());
			return responseUtility.setResponse(output, HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
	@PostMapping(path="/pushToTailOfQueue",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> pushToTailOfQueue(@RequestBody RedisQueue redisQueue) {
		try (Jedis jedis = jedisPool.getResource()) {
			Long output = jedis.rpush(redisQueue.getQueueName(),redisQueue.getQueueValue());
			return responseUtility.setResponse(output, HttpStatus.OK);
		}catch(Exception e) {
			throw e;
		}
	}
}

