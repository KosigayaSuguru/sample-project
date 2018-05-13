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

/**
 * HogeControllerに対して、なるべくアノテーションを使用してテストを実行する。
 * @Mock、@InjectMockを使ってテストを実行している。
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ HogeController.class })
public class HogeControllerTestWithAnnotation {

	@InjectMocks
	private HogeController c = PowerMockito.spy(new HogeController());

	@Mock
	private TestBean b;

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
		PowerMockito.when(b.getA()).thenReturn("mock TestBean.getA()");
		PowerMockito.when(c, "testPublicMethod").thenReturn("public method mock return");
		PowerMockito.when(c, "testPrivateMethod").thenReturn("private method mock return");

		HogeJson hoge = c.hoge();
		assertEquals("public method mock return", hoge.pub);
		assertEquals("private method mock return", hoge.pri);
		assertEquals("mock TestBean.getA()", hoge.testBeanA);
	}

	@Test
	public void testHoge2() throws Exception {
		System.out.println(2);

		// testHoge1()の中で、whenを使ってb.getA()のreturnを設定しているが、testHoge2()実行時には、
		// その設定が生きてないっていう（メソッドセーフみたいな感じ）ことの確認用
		// ※何度か実行して、コンソールに 1 2 null ってでればOK

		System.out.println(b.getA()); // nullになる
	}
}
