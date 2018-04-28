package com.example.demo.testcase.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.DemoApplication;
import com.example.demo.config.AppTestConfig;
import com.example.demo.controller.HogeController;

/**
 * 複数クラスでSpringRunnerで起動している時の確認用で何も検証してないクラス
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DemoApplication.class, AppTestConfig.class })
public class DemoApplicationTests2 {

	@Autowired
	HogeController hogeController;

	@Before
	public void startup() {
		System.out.println(this.getClass().getSimpleName());
	}

	@Test
	public void hogeController() {
	}
}
