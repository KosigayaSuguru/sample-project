package test3.db.entity.dto;

import java.util.List;

import test3.db.entity.Test1;

public class ForD2Test1Dto extends Test1 {

	private List<ForD2Test2Dto> test2List;

	public List<ForD2Test2Dto> getTest2List() {
		return test2List;
	}

	public void setTest2List(List<ForD2Test2Dto> test2List) {
		this.test2List = test2List;
	}

	@Override
	public String toString() {
		return "ForD2Test1Dto [test2List=" + test2List + ", id=" + id + ", test1Col1=" + test1Col1 + ", test1Col2=" + test1Col2 + ", test2=" + test2
				+ "]";
	}
}