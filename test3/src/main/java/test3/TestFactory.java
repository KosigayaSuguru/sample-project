package test3;

import test3.db.entity.TestEntity;

public class TestFactory {

	public TestEntity getTest(){
		TestEntity tmp = new TestEntity();
		tmp.setCol1("TEST_FACTORY_COL1");
		tmp.setCol2("TEST_FACTORY_COL2");
		return tmp;
	}
}
