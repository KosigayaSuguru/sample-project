package com.example.demo.testcase.springboot.jmockito;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.demo.models.TestBean;
import com.example.demo.models.TestBeanChild;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

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

	static public class モック化される範囲を確認する {

		@Before
		public void startup() {
		}

		@Test
		public void モック動作をインスタンスを指定して設定する(@Mocked TestBeanChild child, @Mocked TestBean bean) {
			String methodname = new Object() {
			}.getClass().getEnclosingMethod().getName();
			System.err.println(methodname);

			new Expectations(child, bean) {
				{
					child.getA();
					result = "mock fugaa";

					bean.setA(anyString);
					result = null;
					times = 1;
				}
			};

			System.out.println(child.getA()); // モックで動く
			System.out.println(child.getA()); // モックで動く
			bean.setA("aaaaaaaa");
			System.out.println(bean.getA()); // モックで動く（null）

			TestBean testBean1 = TestBean.newInstance();
			TestBean testBean2 = TestBean.newInstance();
			System.out.println(testBean1.getCldA()); // Expectationsでインスタンスを指定しているのでtestBean1内はモック化されない。
			System.out.println(testBean1.getPrivateCldA()); // Expectationsでインスタンスを指定しているのでtestBean1内はモック化されない。
			System.out.println(testBean2.getCldA()); // Expectationsでインスタンスを指定しているのでtestBean2内はモック化されない。
			System.out.println(testBean2.getPrivateCldA()); // Expectationsでインスタンスを指定しているのでtestBean2内はモック化されない。

			// 処理が実行された後じゃないと検証できない
			new Verifications() {
				{
					child.getA();

					String s;
					bean.setA(s = withCapture());
					System.out.println("test: " + s);
				}
			};
		}

		@Test
		public void モック動作をインスタンスを指定しないで設定する(@Mocked TestBeanChild child, @Mocked TestBean bean) {
			String methodname = new Object() {
			}.getClass().getEnclosingMethod().getName();
			System.err.println(methodname);

			new Expectations() {
				{
					child.getA();
					result = "mock fugaa";

					bean.setA(anyString);
					result = null;
					times = 1;
				}
			};

			System.out.println(child.getA()); // モックで動く
			System.out.println(child.getA()); // モックで動く
			bean.setA("aaaaaaaa");
			System.out.println(bean.getA()); // モックで動く（getA()に対しての動作を指定していないのでnull）

			// bean と testBean1 の参照が一致するようになる
			TestBean testBean1 = TestBean.newInstance();
			System.out.println("bean: " + bean);
			System.out.println("testBean1: " + testBean1);

			TestBean testBean2 = TestBean.newInstance();
			System.out.println(testBean1.getCldA()); // モックで動く(getCldA()に対しての動作を指定していないのでnull)
			System.out.println(testBean1.getPrivateCldA()); // モックで動く(getCldA()に対しての動作を指定していないのでnull)
			System.out.println(testBean2.getCldA()); // モックで動く(getCldA()に対しての動作を指定していないのでnull)
			System.out.println(testBean2.getPrivateCldA()); // モックで動く(getCldA()に対しての動作を指定していないのでnull)

			// 処理が実行された後じゃないと検証できない
			new Verifications() {
				{
					child.getA();

					String s;
					bean.setA(s = withCapture());
					System.out.println("test: " + s);
				}
			};
		}

		@Test
		public void モック動作をインスタンスを指定するしないのミックス(@Mocked TestBeanChild child, @Mocked TestBean bean) {
			String methodname = new Object() {
			}.getClass().getEnclosingMethod().getName();
			System.err.println(methodname);

			new Expectations(bean) {
				{
					child.getA();
					result = "mock fugaa";

					bean.getA();
					result = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

					bean.getCldA();
					result = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";

					bean.setA(anyString);
					result = null;
					times = 1;
				}
			};

			System.out.println(child.getA()); // モックで動く
			System.out.println(child.getA()); // モックで動く
			bean.setA("aaaaaaaa");
			System.out.println(bean.getA()); // モックで動く（getA()に対しての動作を指定していないのでnull）

			// bean と testBean の参照が一致しない
			TestBean testBean1 = TestBean.newInstance();
			TestBean testBean2 = TestBean.newInstance();
			System.out.println("bean: " + bean);
			System.out.println("testBean1: " + testBean1);
			System.out.println("child: " + child);
			System.out.println("testBean1.cld: " + testBean1.cld); // childはモックインスタンスになっている
			System.out.println("testBean1.cld.a: " + testBean1.cld.a); // childはモックインスタンスになっているので、child.a は null になっている
			System.out.println("testBean2.cld: " + testBean2.cld); // childはモックインスタンスになっている
			System.out.println("testBean2.cld.a: " + testBean2.cld.a); // childはモックインスタンスになっているので、child.a は null になっている

			System.out.println(testBean1.getCldA()); // モックで動く(childのgetA()のモックの結果を使って、child.getA()が返っている)
			System.out.println(testBean1.getPrivateCldA()); // モックで動く(childのgetA()のモックの結果を使って、child.getA()が返っている)
			System.out.println(testBean2.getCldA()); // モックで動く(childのgetA()のモックの結果を使って、child.getA()が返っている)
			System.out.println(testBean2.getPrivateCldA()); // モックで動く(childのgetA()のモックの結果を使って、child.getA()が返っている)

			// 処理が実行された後じゃないと検証できない
			new Verifications() {
				{

					String s;
					bean.setA(s = withCapture());
					System.out.println("test: " + s);
				}
			};
		}
	}

	public static class Calendarクラス使って挙動確認 {

		@Before
		public void before() {
			System.out.println("- - - - - - - - - - - - - - - - - - - - ");
		}
		@After
		public void after() {
			System.out.println();
		}
		@Test
		public void etc(@Mocked Calendar cal) {

			new Expectations(Calendar.class) {
				{
					cal.getTimeInMillis();
					result = 20000;
					minTimes = 0;
				}
			};

			System.out.println(cal.getTimeInMillis()); // 部分モックの設定が効いている
			System.out.println(cal.getTimeZone()); // 部分モック設定前からからいるモックインスタンスだからnullになる？
			System.out.println();
			System.out.println(Calendar.getInstance()); // Calendar.getInstance() はモック化していないので非モックの動作
			System.out.println(Calendar.getInstance().getTimeInMillis()); // 実質、Calendar#getTimeInMillis()を実行しているので、部分モックが効いてる
			System.out.println(Calendar.getInstance().getTimeZone()); // 部分モック設定がないメソッドなので非モック動作
			System.out.println();
			Calendar b = Calendar.getInstance(); // 一回、変数に格納しても動作は一緒
			System.out.println(b);
			System.out.println(b.getTimeInMillis());
			System.out.println(b.getTimeZone());
		}

		@Test
		public void factoryから生成されるようなヤツは実際のクラスに対してMockする方が良い1(@Mocked Calendar cal) {

			new Expectations(Calendar.class) {
				{
					cal.getTimeInMillis();
					result = 20001;
					minTimes = 0;

					cal.getCalendarType();
					result = "hogehoge";
					minTimes = 0;
				}
			};
			Calendar a = Calendar.getInstance(); // 一回、変数に格納しても動作は一緒
			System.out.println(a);
			System.out.println(a.getClass());
			System.out.println(a.getTimeInMillis()); // 部分モックの設定が効いている
			System.out.println(a.getCalendarType()); // Calendarに対してのモック設定だとモックの値返してくれない
			System.err.println("Calendarに対してのモック設定だとモックの値返してくれない");
		}

		@Test
		public void factoryから生成されるようなヤツは実際のクラスに対してMockする方が良い2(@Mocked GregorianCalendar cal) {

			new Expectations(GregorianCalendar.class) {
				{
					cal.getTimeInMillis();
					result = 20001;
					minTimes = 0;

					cal.getCalendarType();
					result = "hogehoge";
					minTimes = 0;
				}
			};
			Calendar a = Calendar.getInstance(); // 一回、変数に格納しても動作は一緒
			System.out.println(a);
			System.out.println(a.getClass());
			System.out.println(a.getTimeInMillis()); // 部分モックの設定が効いている
			System.out.println(a.getCalendarType()); // GregorianCalendarに対してのモック設定だとモックの値返してくれる
			System.err.println("GregorianCalendarに対してのモック設定だとモックの値返してくれる");
			System.err.println("※継承先でoverrideしてると継承元に対してのモック設定は無視されるっぽい");
		}
	}
}