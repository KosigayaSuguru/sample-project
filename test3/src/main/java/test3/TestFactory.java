package test3;

public class TestFactory {

	public Test getTest(){
		Test tmp = new Test();
		tmp.setCol1("TEST_FACTORY_COL1");
		tmp.setCol2("TEST_FACTORY_COL2");
		return tmp;
	}
}
