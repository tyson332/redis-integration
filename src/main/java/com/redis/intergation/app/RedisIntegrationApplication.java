package com.redis.intergation.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.redis.intergation")
public class RedisIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisIntegrationApplication.class, args);
	}

}
