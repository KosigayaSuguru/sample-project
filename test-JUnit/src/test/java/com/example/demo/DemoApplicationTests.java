package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.controller.HogeController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

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
