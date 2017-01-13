package test3.db.entity.dto;

import java.util.List;

import test3.db.entity.Test1;
import test3.db.entity.Test2;

public class Test1Test2Dto2 {

	private Test1 test1;
	private List<Test2> test2List;

	public Test1 getTest1() {
		return test1;
	}

	public List<Test2> getTest2List() {
		return test2List;
	}

	public void setTest1(Test1 test1) {
		this.test1 = test1;
	}

	public void setTest2List(List<Test2> test2List) {
		this.test2List = test2List;
	}

	@Override
	public String toString() {
		return "Test1Test2Dto [test1=" + test1 + ", test2List=" + test2List + "]";
	}
}