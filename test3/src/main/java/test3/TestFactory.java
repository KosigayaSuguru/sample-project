package test3;

import test3.db.entity.Test1Entity;

public class TestFactory {

	public Test1Entity getTest(){
		Test1Entity tmp = new Test1Entity();
		tmp.setCol1("TEST_FACTORY_COL1");
		tmp.setCol2("TEST_FACTORY_COL2");
		return tmp;
	}
}
