package test3.web.controller;

// View–¼‚ð’è‹`‚·‚é
public enum TestEnumView {

	TEST_VIEW_TYMELEAF_SAMLE("hoge"),
	TEST_VIEW_VELOCITY_SAMPLE("velocityTemplate"),
	TEST_VIEW_VELOCITY_SESSION_TEST("velocitySessionTest"),
	TEST_VIEW_VELOCITY_SPRING_BIND_TEST("velocitySpringBindTest"),
	TEST_VIEW_VELOCITY_DYNAMIC_FORM_TEST("velocityDynamicFormTest")
	;

	private String viewName;

	private TestEnumView(String value) {
		this.viewName = value;
	}

	public String getViewName() {
		return viewName;
	}
}
