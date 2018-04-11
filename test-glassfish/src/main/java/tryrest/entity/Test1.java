package tryrest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Test1 {

	public static final String TABLE = "TEST1";

	/**
	 * id:int(10) <Primary Key>
	 */
	@Id
	private int id;

	/**
	 * test1_col1:int(10)
	 */
	private int test1_col1;

	/**
	 * test1_col2:int(10)
	 */
	private int test1_col2;

	/**
	 * Constractor
	 */
	public Test1() {
	}

	/**
	 * Constractor
	 *
	 * @param <code>id</code>
	 */
	public Test1(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTest1_col1() {
		return this.test1_col1;
	}

	public void setTest1_col1(int test1_col1) {
		this.test1_col1 = test1_col1;
	}

	public int getTest1_col2() {
		return this.test1_col2;
	}

	public void setTest1_col2(int test1_col2) {
		this.test1_col2 = test1_col2;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[Test1Vo:");
		buffer.append(" id: ");
		buffer.append(id);
		buffer.append(" test1_col1: ");
		buffer.append(test1_col1);
		buffer.append(" test1_col2: ");
		buffer.append(test1_col2);
		buffer.append("]");
		return buffer.toString();
	}

}
