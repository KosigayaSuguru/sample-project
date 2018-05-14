package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AsyncTest;
import com.example.demo.Hoge;

import mapper.Test3Mapper;
import mapper.Test3Vo;

@RestController
public class TestController {

	@Value("${hoge.hoge}")
	String aaaa;

	@Autowired
	Hoge hoge;

	@Autowired
	Test3Mapper test3Mapper;

	@Autowired
	AsyncTest async;

	@Transactional
	@GetMapping(value = "/dbtest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Test3Vo> hoge() {

		System.err.println(aaaa);
		System.err.println(hoge.test());
		return test3Mapper.get();
	}

	/*
	 * Async アノテーションを使った非同期実行のテスト
	 */
	@GetMapping(value = "/async_test", produces = MediaType.TEXT_PLAIN_VALUE)
	public String asyncTest() {
		try {
			async.asyncMethod(100);
			async.asyncMethod(200);
			async.asyncMethod(300);
		} catch (Exception e) {
		}

		return "asyncTest:"+ LocalDateTime.now();
	}
}
