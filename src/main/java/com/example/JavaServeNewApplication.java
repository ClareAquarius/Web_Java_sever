package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.*.mapper")
//@MapperScan 注解是 MyBatis 框架中的一个注解，用于扫描指定包下的 Mapper 接口，并将其注册为 Spring Bean
public class JavaServeNewApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaServeNewApplication.class, args);
	}

}


