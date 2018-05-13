package com.example.demo.testcase.springboot;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.DemoApplication;
import com.example.demo.config.AppTestConfig;
import com.example.demo.controller.HogeController;
import com.example.demo.controller.HogeController.HogeJson;
import com.example.demo.models.TestBean;
import com.example.demo.models.TestBeanChild;

/**
 * Autowired した HogeController の中の testBean をSpy化して、testBeanの中の testBeanChild をモック化するテスト
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { DemoApplication.class, AppTestConfig.class })
public class DemoApplicationTests4 {

	// test/resources/application.yaml 内の設定値
	@Value("${spring.profiles.active}")
	String profile;

	@Autowired
	@InjectMocks
	HogeController hogeController;

	@InjectMocks
	private TestBean testBeanSpy = PowerMockito.spy(new TestBean());

	@Mock
	private TestBeanChild testBeanChildMock;

	@Before
	public void startup() {
		System.out.println(this.getClass().getSimpleName());
		System.out.println(String.format("profile is %s", profile));
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void hogeController() {
		PowerMockito.when(testBeanSpy.getA()).thenReturn("mock TestBean.getA()");
		PowerMockito.when(testBeanChildMock.getA()).thenReturn("mock TestBeanChild.getA()");
		HogeJson hoge = hogeController.hoge();

		// HogeControllerの中でmock化されてないところ
		assertEquals("testPublicMethod", hoge.pub);
		assertEquals("testPrivateMethod", hoge.pri);

		// HogeControllerの中のTestBeanをmockしたところ
		assertEquals("mock TestBean.getA()", hoge.testBeanA);
		// HogeControllerの中のTestBeanの中のTestBeanChildをmockしたところ
		assertEquals("mock TestBeanChild.getA()", hoge.testBeanChildA);

		System.out.println();
	}
}
