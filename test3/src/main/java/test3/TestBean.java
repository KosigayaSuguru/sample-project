package test3;

import java.util.function.Consumer;

public class TestBean {

	private String hoge;
	private String moge;
	private String catalinaHome;
	private TestEnumStatus testStatus;

	public String getHoge() {
		return hoge;
	}

	public void setHoge(String hoge) {
		this.hoge = hoge;
	}

	public String getMoge() {
		return moge;
	}

	public void setMoge(String moge) {
		this.moge = moge;
	}

	public String getCatalinaHome() {
		return catalinaHome;
	}

	public void setCatalinaHome(String catalinaHome) {
		this.catalinaHome = catalinaHome;
	}

	public void ramdaTestMyfunc(TestIf hoge) {
		System.out.println("----------------");
		hoge.hoge("ramdaTestMyfunc");
		System.out.println("----------------");
	}

	public void ramdaTest(Consumer<String> hoge) {
		System.out.println("----------------");
		hoge.accept("ramdaTest");
		System.out.println("----------------");
	}

	public TestEnumStatus getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(TestEnumStatus testStatus) {
		this.testStatus = testStatus;
	}
}
