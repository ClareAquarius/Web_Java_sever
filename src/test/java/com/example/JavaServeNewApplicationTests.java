package com.example;


import com.example.generator.entity.User;
import com.example.generator.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class JavaServeNewApplicationTests {
	@Resource
	private UserMapper userMapper;
	@Test
	void test(){
		List<User> users =userMapper.selectList(null);
		users.forEach(System.out::println);
	}
}
