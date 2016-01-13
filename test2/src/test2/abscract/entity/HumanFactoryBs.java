package test2.abscract.entity;

import test2.Interface.HumanFactory;
import test2.impl.factory.HumanAFactory;
import test2.impl.factory.HumanBFactory;

public abstract class HumanFactoryBs implements HumanFactory {

	public static HumanFactory selectFactory(int a){
		if(a == 1){
			return new HumanAFactory();
		} else {
			return new HumanBFactory();
		}
	}
}
