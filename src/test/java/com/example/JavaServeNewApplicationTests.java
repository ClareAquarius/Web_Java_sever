package com.example;

import com.example.generator.entity.Pcomment;
import com.example.generator.mapper.PcommentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class JavaServeNewApplicationTests {
	@Resource
	private PcommentMapper pcommentMapper;

	@Test
	void test(){
		Pcomment pcomment = new Pcomment();
		pcomment.setUserid(245);
		pcomment.setPctext("48464");
		pcomment.setPtargetid(48);
		int k=pcommentMapper.insertPcomment(pcomment);
		System.out.println(k);
		System.out.println(pcomment.getPcommentid());
	}
}
