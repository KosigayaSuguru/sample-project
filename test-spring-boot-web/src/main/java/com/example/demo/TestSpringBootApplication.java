package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("mapper")
public class TestSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSpringBootApplication.class, args);
	}
}
