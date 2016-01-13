package test2.abscract.entity;

import test2.Interface.Human;

public abstract class HumanBs implements Human {
	abstract public String work();

	public String eat(){
		return "eat";
	}

	public String sleep(){
		return "sleep";
	}

}
