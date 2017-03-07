package test3.web.form;

import java.io.Serializable;

@SuppressWarnings("serial")
public class JsonRequestForm implements Serializable {

	Integer num;
	String str;

	public Integer getNum() {
		return num;
	}

	public String getStr() {
		return str;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public void setStr(String str) {
		this.str = str;
	}

}
