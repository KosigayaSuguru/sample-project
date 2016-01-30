package test3;

// View–¼‚ð’è‹`‚·‚é
public enum TestEnumView {

	TEST_VIEW_TYMELEAF_SAMLE("hoge"),
	TEST_VIEW_VELOCITY_SAMPLE("velocityTemplate");

	private String viewName;

	private TestEnumView(String value) {
		this.viewName = value;
	}

	public String getViewName() {
		return viewName;
	}
}
