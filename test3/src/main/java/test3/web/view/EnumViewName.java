package test3.web.view;

// View–¼‚ð’è‹`‚·‚é
public enum EnumViewName {

	TYMELEAF_SAMLE("hoge", "hogeSp"),
	VELOCITY_SAMPLE("velocityTemplate"),
	VELOCITY_SESSION_TEST("velocitySessionTest"),
	VELOCITY_SPRING_BIND_TEST("velocitySpringBindTest"),
	VELOCITY_DYNAMIC_FORM_TEST("velocityDynamicFormTest");

	private String viewNamePc;
	private String viewNameSp;

	private EnumViewName(String pc, String sp) {
		this.viewNamePc = pc;
		this.viewNameSp = sp;
	}

	private EnumViewName(String pc) {
		this.viewNamePc = pc;
		this.viewNameSp = null;
	}

	public String getViewName() {
		return viewNamePc;
	}

	public String getViewNamePc() {
		return viewNamePc;
	}

	public String getViewNameSp() {
		return viewNameSp;
	}
}
