package test2.impl.factory;

import test2.Interface.Human;
import test2.Interface.HumanFactory;
import test2.impl.entity.HumanA;

public class HumanAFactory implements HumanFactory{

	public Human create() {
		return new HumanA();
	}
}
