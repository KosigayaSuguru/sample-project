package test3;

// DB�}�X�^�������ŃR�[�h�l�����ꍇ�A�ϐ�code�ɃR�[�h�l���i�[���A���̒l���g����
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
