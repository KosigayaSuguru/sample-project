package com.example.demo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class TestBean {

	public String a = "TestBean a";
	public String b = "TestBean b";
	public String c = "TestBean c";

	@Autowired
	public TestBeanChild cld;

	public String getA() {
		return a;
	}

	public String getB() {
		return b;
	}

	public String getC() {
		return c;
	}
	
	public String getCldA() {
		return cld.getA();
	}
}
