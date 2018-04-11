package com.example.demo;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import mapper.Test3Mapper;
import mapper.Test3Vo;

@SpringBootApplication
@RestController
@MapperScan("mapper")
public class TestSpringBootApplication {

	@Value("${hoge.hoge}")
	String aaaa;

	@Autowired
	Hoge hoge;

	@Autowired
	Test3Mapper	test3Mapper;

	public static void main(String[] args) {
		SpringApplication.run(TestSpringBootApplication.class, args);
	}

	@Transactional
	@GetMapping(value = "/hoge", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Test3Vo> hoge() {

		System.err.println(aaaa);
		System.err.println(hoge.test());
		return test3Mapper.get();
	}
}
