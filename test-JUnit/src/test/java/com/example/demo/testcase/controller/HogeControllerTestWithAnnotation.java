package com.example.demo.testcase.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.example.demo.controller.HogeController;
import com.example.demo.controller.HogeController.HogeJson;
import com.example.demo.models.TestBean;
import com.example.demo.models.TestBeanChild;

/**
 * HogeController に対して、なるべくアノテーションを使用してテストを実行する。
 * @Mock、@InjectMock を使ってテストを実行している。
 * @Spy は使おうとするとエラーがでるので、PowerMockito.spy() を使っている。
 * 
 * 構成としては、
 * ・TestBean の中で使用している TestBeanChild のモックとして、testBeanChildMock を使う (testBeanSpy に@InjectMocksを付与している)
 * ・HogeController の中で使用している TestBean のモックとして、testBeanSpy を使う (hogeControllerSpy に@InjectMocksを付与している)
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ HogeController.class })
public class HogeControllerTestWithAnnotation {

	@InjectMocks
	private HogeController hogeControllerSpy = PowerMockito.spy(new HogeController());

	@InjectMocks
	private TestBean testBeanSpy = PowerMockito.spy(new TestBean());

	@Mock
	private TestBeanChild testBeanChildMock;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 特定のフィールドにモックをインジェクトして、テストを実行する ※インジェクトが絡むところだけ抜粋 ※アノテーションでの設定多めVer
	 *
	 * @throws Exception
	 */
	@Test
	public void testHoge() throws Exception {
		System.out.println(1);

		// 極力アノテーションを使ってモックの生成、インジェクトを設定するVer
		// @Spyを使おうとするとエラーが出るので、Spy()だけPowerMockitoのメソッドを直接呼び出している

		// モックの設定
		PowerMockito.when(testBeanSpy.getA()).thenReturn("mock TestBean.getA()");
		PowerMockito.when(testBeanChildMock.getA()).thenReturn("mock TestBeanChild.getA()");
		PowerMockito.when(hogeControllerSpy, "testPublicMethod").thenReturn("public method mock return");
		PowerMockito.when(hogeControllerSpy, "testPrivateMethod").thenReturn("private method mock return");

		HogeJson hoge = hogeControllerSpy.hoge();
		assertEquals("public method mock return", hoge.pub);
		assertEquals("private method mock return", hoge.pri);
		assertEquals("mock TestBean.getA()", hoge.testBeanA);
		assertEquals("mock TestBeanChild.getA()", hoge.testBeanChildA);
	}

	@Test
	public void testHoge2() throws Exception {
		System.out.println(2);

		// testHoge1()の中で、whenを使ってb.getA()のreturnを設定しているが、testHoge2()実行時には、
		// その設定が生きてないっていう（メソッドセーフみたいな感じ）ことの確認用
		// ※何度か実行して、コンソールに 1 2 null ってでればOK

		System.out.println(testBeanSpy.getA()); // nullになる
	}
}
