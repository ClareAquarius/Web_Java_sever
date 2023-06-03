package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class JavaServeNewApplicationTests {
	@Resource
	private RedisTemplate redisTemplate;

	@Test
	void test(){

		String key="1";
		String loginuser="asada";
		redisTemplate.opsForValue().set(key,loginuser,30, TimeUnit.MINUTES);
	}
}
