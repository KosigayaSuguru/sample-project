package test3.db.entity.dto;

import test3.db.entity.Test1;
import test3.db.entity.Test2;

public class Test1Test2Dto2 {

	private Test1 test1;
	private Test2 test2;

	public Test1 getTest1() {
		return test1;
	}

	public Test2 getTest2() {
		return test2;
	}

	public void setTest1(Test1 test1) {
		this.test1 = test1;
	}

	public void setTest2(Test2 test2) {
		this.test2 = test2;
	}

	@Override
	public String toString() {
		return "Test1Test2Dto2 [test1=" + test1 + ", test2=" + test2 + "]";
	}
}