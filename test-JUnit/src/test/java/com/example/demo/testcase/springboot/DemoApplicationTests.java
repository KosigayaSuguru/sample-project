package com.example.demo.testcase.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.DemoApplication;
import com.example.demo.config.AppTestConfig;
import com.example.demo.controller.HogeController;

/**
 * 下記の確認用
 * test側で、JUnitを実行したときに、test/resourceのapplication.yamlが使われること
 * main側と同じ名前のBean(ここでは AppTestConfig.java, AppTestSubConfig.java)を@SpringBootTestで指定すると、test側が優先されること
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DemoApplication.class, AppTestConfig.class })
public class DemoApplicationTests {

	// test/resources/application.yaml 内の設定値
	@Value("${spring.profiles.active}")
	String profile;

	@Autowired
	HogeController hogeController;

	@Before
	public void startup() {
		System.out.println(this.getClass().getSimpleName());
		System.out.println(String.format("profile is %s", profile));
	}

	@Test
	public void hogeController() {
	}
}
