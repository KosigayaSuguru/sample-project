package com.example.demo.testcase.springboot.jmockito;

import org.junit.Before;
import org.junit.Test;

import com.example.demo.models.TestBean;
import com.example.demo.models.TestBeanChild;

import mockit.Expectations;
import mockit.Mocked;

/**
 * JUnitより、JMockitoのライブラリを先に読む必要がある(pom内のdependencyの位置を変えるで良い) JDKを使う必要がある
 */
// @RunWith(SpringRunner.class)
// @SpringBootTest(classes = { DemoApplication.class, AppTestConfig.class })
public class JMockitoTests {

	static public class startupで設定したmockがメソッド全体に効いているのを確認する {

		@Mocked
		TestBeanChild a;

		@Before
		public void startup() {
			new Expectations() {
				{
					a.getA();
					result = "Mock";
				}
			};
		}

		@Test
		public void JMockitテスト() {
			TestBean testBean = TestBean.newInstance();

			String methodname = new Object() {
			}.getClass().getEnclosingMethod().getName();

			String className = this.getClass().getSimpleName();
			System.out.println(
					String.format("%s %s %s %s", className, methodname, testBean.getCldA(), testBean.getPrivateCldA()));
		}

		@Test
		public void JMockitテスト2() {
			TestBean testBean = TestBean.newInstance();

			String methodname = new Object() {
			}.getClass().getEnclosingMethod().getName();

			String className = this.getClass().getSimpleName();
			System.out.println(
					String.format("%s %s %s %s", className, methodname, testBean.getCldA(), testBean.getPrivateCldA()));
		}
	}

	static public class privateにもモックが適用されているのを確認する {

		@Mocked
		TestBeanChild a;

		@Before
		public void startup() {
			new Expectations() {
				{
					a.getA();
					result = "called privateMethodMock";
				}
			};
		}

		@Test
		public void JMockitテスト() {
			TestBean testBean = TestBean.newInstance();

			String methodname = new Object() {
			}.getClass().getEnclosingMethod().getName();

			String className = this.getClass().getSimpleName();
			System.out.println(String.format("%s %s %s", className, methodname, testBean.getPrivateCldA()));
		}
	}

	static public class テストクラスが分かれた場合mockが効いていないことを確認する {

		@Before
		public void startup() {
		}

		@Test
		public void JMockitテスト() {
			TestBean testBean = TestBean.newInstance();

			String methodname = new Object() {
			}.getClass().getEnclosingMethod().getName();

			String className = this.getClass().getSimpleName();
			System.out.println(
					String.format("%s %s %s %s", className, methodname, testBean.getCldA(), testBean.getPrivateCldA()));
		}
	}
}