package com.example.demo.testcase.springboot.jmockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.DemoApplication;
import com.example.demo.config.AppTestConfig;
import com.example.demo.controller.HogeController;
import com.example.demo.models.TestBean;

import mockit.Expectations;
import mockit.Mocked;

/**
 * JUnitより、JMockitoのライブラリを先に読む必要がある(pom内のdependencyの位置を変えるで良い) 
 * JDKを使う必要がある
 */
// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = { DemoApplication.class, AppTestConfig.class })
public class JMockitoTests {

	HogeController hogeController;

	@Mocked
	TestBean a;

	@Before
	public void startup() {
	}

	@Test
	public void JMockitテスト() {
		new Expectations() {
			{
				a.getA();
				result = "Mock";
			}
		};
		System.out.println(a.getA());
	}
}
