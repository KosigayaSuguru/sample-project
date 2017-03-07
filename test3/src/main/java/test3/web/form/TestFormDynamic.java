package test3.web.form;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@SuppressWarnings("serial")
public class TestFormDynamic extends AbstractForm {

	@Pattern(regexp = "[A-Z]")
	String test;

	@Valid
	List<TestFormDynamicNames> name;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public List<TestFormDynamicNames> getName() {
		return name;
	}

	public void setName(List<TestFormDynamicNames> name) {
		this.name = name;
	}
}
