package test3;

import java.io.Serializable;

public class TestSession implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public String str1;

	public String getStr1() {
		return str1;
	}

	public void setStr1(String str1) {
		this.str1 = str1;
	}

	public String getMessage1() {
		return "TestSession#getMessage1";
	}

	public static String getMessage2() {
		return "TestSession#getMessage2";
	}

	public boolean isTrue(String test) {
		switch (test) {
		case "ok":
			return true;
		case "ng":
			return false;
		default:
			return true;
		}
	}
}
