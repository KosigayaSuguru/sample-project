package test3.app.dto.ex;

import java.util.List;

import test3.app.dto.bs.Test1EntityDto;
import test3.app.dto.bs.Test2EntityDto;

public class Test1Test2EntityDto {

	private Test1EntityDto test1;
	private List<Test2EntityDto> test2List;

	public Test1EntityDto getTest1() {
		return test1;
	}

	public List<Test2EntityDto> getTest2List() {
		return test2List;
	}

	public void setTest1(Test1EntityDto test1) {
		this.test1 = test1;
	}

	public void setTest2List(List<Test2EntityDto> test2List) {
		this.test2List = test2List;
	}

	@Override
	public String toString() {
		return "Test1Test2EntityDto [test1=" + test1 + ", test2List=" + test2List + "]";
	}
}