package mapper;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Test3Vo {

	public static final String TABLE = "TEST3";

	/**
	 * id:int(10) <Primary Key>
	 */
	private int id;

	/**
	 * test2_id:int(10)
	 */
	private int test2_id;

	/**
	 * enum_test:enum(9)
	 */
	private String enum_test;

	/**
	 * update_date:timestamp(0)
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime update_date;

	public Test3Vo() {
	}

	public Test3Vo(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTest2_id() {
		return this.test2_id;
	}

	public void setTest2_id(int test2_id) {
		this.test2_id = test2_id;
	}

	public String getEnum_test() {
		return this.enum_test;
	}

	public void setEnum_test(String enum_test) {
		this.enum_test = enum_test;
	}

	public LocalDateTime getUpdate_date() {
		return this.update_date;
	}

	public void setUpdate_date(LocalDateTime update_date) {
		this.update_date = update_date;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[Test3Vo:");
		buffer.append(" id: ");
		buffer.append(id);
		buffer.append(" test2_id: ");
		buffer.append(test2_id);
		buffer.append(" enum_test: ");
		buffer.append(enum_test);
		buffer.append(" update_date: ");
		buffer.append(update_date);
		buffer.append("]");
		return buffer.toString();
	}

}
