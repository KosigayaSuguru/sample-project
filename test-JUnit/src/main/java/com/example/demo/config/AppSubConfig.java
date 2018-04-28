package com.example.demo.config;

import org.springframework.context.annotation.Bean;

import com.example.demo.models.TestBean;

public class AppSubConfig {

	@Bean
	TestBean testBean2() {
		TestBean t = new TestBean();
		t.a = String.format("%s 2", t.a);
		t.b = String.format("%s 2", t.b);
		t.c = String.format("%s 2", t.c);
		return t;
	}
}
