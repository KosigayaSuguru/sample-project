package com.example.demo.controller;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.example.demo.controller.HogeController.HogeJson;
import com.example.demo.models.TestBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ HogeController.class })
public class HogeControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHoge() throws Exception {
		HogeController c = PowerMockito.spy(new HogeController());
		TestBean b = PowerMockito.mock(TestBean.class);

		PowerMockito.when(b.getA()).thenReturn("mock TestBean.getA()");
		PowerMockito.when(c, "testPublicMethod").thenReturn("public method mock return");
		PowerMockito.when(c, "testPrivateMethod").thenReturn("private method mock return");
		Whitebox.setInternalState(c, "testBean", b);

		HogeJson hoge = c.hoge();
		assertEquals("public method mock return", hoge.pub);
		assertEquals("private method mock return", hoge.pri);
		assertEquals("mock TestBean.getA()", hoge.testBeanA);
	}

	@Test
	public void testTestPublicMethod() {
		HogeController c = new HogeController();
		String r = c.testPublicMethod();
		assertEquals("testPublicMethod", r);
	}

	@Test
	public void testTestPrivateMethod() throws Exception {
		HogeController c = new HogeController();
		Method m = HogeController.class.getDeclaredMethod("testPrivateMethod");
		m.setAccessible(true);

		String r = (String) m.invoke(c);
		assertEquals("testPrivateMethod", r);
	}
}
