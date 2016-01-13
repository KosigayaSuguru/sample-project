package test2.main;

import java.util.ArrayList;

import test2.Interface.Human;
import test2.Interface.HumanFactory;
import test2.abscract.entity.HumanFactoryBs;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		ArrayList<Human> humanList = new ArrayList<>();

		HumanFactory factory = null;

		factory = HumanFactoryBs.selectFactory(1);
		humanList.add(factory.create());

		factory = HumanFactoryBs.selectFactory(0);
		humanList.add(factory.create());

		for(Human man : humanList){
			String  message = "";
			message = man.work();
			System.out.println(message);
			message = man.eat();
			System.out.println(message);
			message = man.sleep();
			System.out.println(message);
		}
	}
}
