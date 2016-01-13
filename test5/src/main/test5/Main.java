package main.test5;

import java.util.ArrayList;

import factory.test5.ServiceFactory;
import inter.test5.Service;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		ArrayList<String> service = new ArrayList<String>();
		service.add("TEST1");
		service.add("TEST2");

		for(String str : service){
			Service hoge = ServiceFactory.create(str);
			hoge.run();
		}

		for(String arg : args){
			System.out.println(arg);
		}
	}
}
