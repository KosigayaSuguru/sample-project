package test3.db.entity;

import java.util.List;

public class Test1 {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column test1.id
	 * @mbg.generated  Sat Jan 14 23:45:30 JST 2017
	 */
	protected Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column test1.test1_col1
	 * @mbg.generated  Sat Jan 14 23:45:30 JST 2017
	 */
	protected Integer test1Col1;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column test1.test1_col2
	 * @mbg.generated  Sat Jan 14 23:45:30 JST 2017
	 */
	protected Integer test1Col2;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column test1.id
	 * @return  the value of test1.id
	 * @mbg.generated  Sat Jan 14 23:45:30 JST 2017
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column test1.id
	 * @param id  the value for test1.id
	 * @mbg.generated  Sat Jan 14 23:45:30 JST 2017
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column test1.test1_col1
	 * @return  the value of test1.test1_col1
	 * @mbg.generated  Sat Jan 14 23:45:30 JST 2017
	 */
	public Integer getTest1Col1() {
		return test1Col1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column test1.test1_col1
	 * @param test1Col1  the value for test1.test1_col1
	 * @mbg.generated  Sat Jan 14 23:45:30 JST 2017
	 */
	public void setTest1Col1(Integer test1Col1) {
		this.test1Col1 = test1Col1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column test1.test1_col2
	 * @return  the value of test1.test1_col2
	 * @mbg.generated  Sat Jan 14 23:45:30 JST 2017
	 */
	public Integer getTest1Col2() {
		return test1Col2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column test1.test1_col2
	 * @param test1Col2  the value for test1.test1_col2
	 * @mbg.generated  Sat Jan 14 23:45:30 JST 2017
	 */
	public void setTest1Col2(Integer test1Col2) {
		this.test1Col2 = test1Col2;
	}

	protected List<Test2> test2;

	public List<Test2> getTest2() {
		return test2;
	}

	public void setTest2(List<Test2> test2) {
		this.test2 = test2;
	}

	@Override
	public String toString() {
		return "Test1 [id=" + id + ", test1Col1=" + test1Col1 + ", test1Col2=" + test1Col2 + ", test2=" + test2 + "]";
	}
}