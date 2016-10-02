package test3.db.entity;

public class Test1Entity {

	private int id;

	private int test1Col1;

	private int test1Col2;

	public int getId() {
		return id;
	}

	public int getTest1Col1() {
		return test1Col1;
	}

	public int getTest1Col2() {
		return test1Col2;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTest1Col1(int test1Col1) {
		this.test1Col1 = test1Col1;
	}

	public void setTest1Col2(int test1Col2) {
		this.test1Col2 = test1Col2;
	}

	@Override
	public String toString() {
		return "Test1Entity [id=" + id + ", test1Col1=" + test1Col1 + ", test1Col2=" + test1Col2 + "]";
	}
}
