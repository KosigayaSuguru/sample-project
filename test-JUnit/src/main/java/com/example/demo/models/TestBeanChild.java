package com.example.demo.models;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class TestBeanChild {

	public String a = "TestBeanChild a";

	public String getA() {
		return a;
	}
}
