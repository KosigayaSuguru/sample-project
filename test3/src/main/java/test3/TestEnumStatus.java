package test3;

// DBマスタ向け等でコード値を持つ場合、変数codeにコード値を格納し、その値を使う事
public enum TestEnumStatus {

	TEST_STATUS1(1), TEST_STATUS2(11), TEST_STATUS3(111);

	private int code;

	private TestEnumStatus(int value){
		this.code = value;
	}

	public int getCode() {
		return code;
	}
}
