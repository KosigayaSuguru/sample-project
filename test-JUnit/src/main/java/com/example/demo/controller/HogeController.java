package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.TestBean;

@RestController
public class HogeController {

	@Autowired
	TestBean testBean;

	@GetMapping("/hoge")
	HogeJson hoge() {

		String r = testPublicMethod();
		String rr = testPrivateMethod();

		HogeJson a = new HogeJson();
		a.field1 = "field1val";
		a.field2 = "field2val";
		a.field3 = "field3val";
		a.field4 = "field4val";
		a.pub = r;
		a.pri = rr;
		a.testBeanA = testBean.getA();

		return a;
	}

	private String testPrivateMethod() {
		return "testPrivateMethod";
	}

	public String testPublicMethod() {
		return "testPublicMethod";
	}

	@SuppressWarnings("unused")
	public static class HogeJson {
		public String field1;
		public String field2;
		public String field3;
		public String field4;
		public String pub;
		public String pri;
		public String testBeanA;
	}
}
