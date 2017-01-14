package test3.db.entity.dto;

import java.util.List;

import test3.db.entity.Test1;
import test3.db.entity.Test2;

public class Test1Test2Dto extends Test1 {

	private List<Test2> test2List;

	public List<Test2> getTest2List() {
		return test2List;
	}

	public void setTest2List(List<Test2> test2List) {
		this.test2List = test2List;
	}

	@Override
	public String toString() {
		return "Test1Test2Dto [test2List=" + test2List + ", id=" + id + ", test1Col1=" + test1Col1 + ", test1Col2=" + test1Col2 + "]";
	}
}