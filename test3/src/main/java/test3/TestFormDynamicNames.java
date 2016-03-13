package test3;

import javax.validation.constraints.Pattern;

public class TestFormDynamicNames {

	@Pattern(regexp = "[a-z]")
	public String name1;

	@Pattern(regexp = "[A-Z]")
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

	@Override
	public String toString(){
		return "[name1]:"+name1 + " " + "[name2]:"+name2;
	}

}
