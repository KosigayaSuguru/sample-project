package com.example.demo.models;

import org.springframework.stereotype.Component;

@Component
public class TestBean {

	public String a = "TestBean a";
	public String b = "TestBean b";
	public String c = "TestBean c";

	public String getA() {
		return a;
	}

	public String getB() {
		return b;
	}

	public String getC() {
		return c;
	}

}
