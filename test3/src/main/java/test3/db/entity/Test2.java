package test3.db.entity;

import java.util.List;

public class Test2 {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column test2.id
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	protected Integer id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column test2.test1_id
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	protected Integer test1Id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column test2.test2_col1
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	protected Integer test2Col1;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column test2.test2_col2
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	protected Integer test2Col2;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column test2.id
	 * @return  the value of test2.id
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column test2.id
	 * @param id  the value for test2.id
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column test2.test1_id
	 * @return  the value of test2.test1_id
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	public Integer getTest1Id() {
		return test1Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column test2.test1_id
	 * @param test1Id  the value for test2.test1_id
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	public void setTest1Id(Integer test1Id) {
		this.test1Id = test1Id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column test2.test2_col1
	 * @return  the value of test2.test2_col1
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	public Integer getTest2Col1() {
		return test2Col1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column test2.test2_col1
	 * @param test2Col1  the value for test2.test2_col1
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	public void setTest2Col1(Integer test2Col1) {
		this.test2Col1 = test2Col1;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column test2.test2_col2
	 * @return  the value of test2.test2_col2
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	public Integer getTest2Col2() {
		return test2Col2;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column test2.test2_col2
	 * @param test2Col2  the value for test2.test2_col2
	 * @mbg.generated  Sun Jan 15 03:16:14 JST 2017
	 */
	public void setTest2Col2(Integer test2Col2) {
		this.test2Col2 = test2Col2;
	}

	protected List<Test3> test3;

	public List<Test3> getTest3() {
		return test3;
	}

	public void setTest3(List<Test3> test3) {
		this.test3 = test3;
	}

	@Override
	public String toString() {
		return "Test2 [id=" + id + ", test1Id=" + test1Id + ", test2Col1=" + test2Col1 + ", test2Col2=" + test2Col2 + ", test3=" + test3 + "]";
	}
}