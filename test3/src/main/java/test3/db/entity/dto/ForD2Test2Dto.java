package test3.db.entity.dto;

import java.util.List;

import test3.db.entity.Test2;
import test3.db.entity.Test3;

public class ForD2Test2Dto extends Test2 {

	private List<Test3> test3List;

	public List<Test3> getTest3List() {
		return test3List;
	}

	public void setTest3List(List<Test3> test3List) {
		this.test3List = test3List;
	}

	@Override
	public String toString() {
		return "ForD2Test2Dto [test3List=" + test3List + ", id=" + id + ", test1Id=" + test1Id + ", test2Col1=" + test2Col1 + ", test2Col2="
				+ test2Col2 + ", test3=" + test3 + "]";
	}

}