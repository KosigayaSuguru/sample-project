package test3.web.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import test3.TestAnnotation;

public class TestForm extends AbstractForm {

	@NotEmpty
	public String name1;

	@Size(max = 4, min = 2)
	public String name2;

	@NotBlank(message = "{Error.validate.message.blank}")
	public String name3;

	@TestAnnotation(message = "{Error.validate.message.testAnnotation}", field="FIELD_TEST")
	@NotEmpty
	public String name4;

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getName4() {
		return name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

}
