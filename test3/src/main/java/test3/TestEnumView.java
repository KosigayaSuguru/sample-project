package test3;

// View名を定義する
public enum TestEnumView {

	TEST_VIEW_TYMELEAF_SAMLE("hoge"),
	TEST_VIEW_VELOCITY_SAMPLE("velocityTemplate"),
	TEST_VIEW_VELOCITY_SESSION_TEST("velocitySessionTest");

	private String viewName;

	private TestEnumView(String value) {
		this.viewName = value;
	}

	public String getViewName() {
		return viewName;
	}
}
