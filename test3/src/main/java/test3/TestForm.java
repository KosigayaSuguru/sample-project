package test3;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TestForm {

	@NotEmpty
	public String name1;
	@Size(max = 4, min = 2)
	public String name2;

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

}
