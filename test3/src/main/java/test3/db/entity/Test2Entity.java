package test3.db.entity;

public class Test2Entity {

	private int id;

	private int test1Id;

	private int test2Col1;

	private int test2Col2;

	public int getId() {
		return id;
	}

	public int getTest1Id() {
		return test1Id;
	}

	public int getTest2Col1() {
		return test2Col1;
	}

	public int getTest2Col2() {
		return test2Col2;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTest1Id(int test1Id) {
		this.test1Id = test1Id;
	}

	public void setTest2Col1(int test2Col1) {
		this.test2Col1 = test2Col1;
	}

	public void setTest2Col2(int test2Col2) {
		this.test2Col2 = test2Col2;
	}

	@Override
	public String toString() {
		return "Test2Entity [id=" + id + ", test1Id=" + test1Id + ", test2Col1=" + test2Col1 + ", test2Col2="
				+ test2Col2 + "]";
	}
}
