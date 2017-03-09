package test3.web.form;

import java.io.Serializable;
import java.util.ArrayList;

import javax.validation.constraints.Max;

@SuppressWarnings("serial")
public class JsonRequestForm implements Serializable {

	Integer num;
	String str;
	ArrayList<ErrorMessageObj> errors = new ArrayList<>();

	@Max(message = "over {value}", value = 10)
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

	public ArrayList<ErrorMessageObj> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<ErrorMessageObj> errors) {
		this.errors = errors;
	}

	public class ErrorMessageObj {
		public String field;
		public String message;
	}
}
