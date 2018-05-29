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

	private TestBeanChild privateCld;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
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

	public String getPrivateCldA() {
		return privateCld.getA();
	}

	public static TestBean newInstance() {

		TestBean testBean = new TestBean();
		TestBeanChild testBeanChild = new TestBeanChild();
		testBean.cld = testBeanChild;

		TestBeanChild testBeanChild2 = new TestBeanChild();
		testBean.privateCld = testBeanChild2;
		return testBean;
	}
}
