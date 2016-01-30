package main.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import factory.test5.ServiceFactory;
import inter.test5.Service;

public class Main {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		ArrayList<String> service = new ArrayList<String>();
		service.add("TEST1");
		service.add("TEST2");

		class Test {
			public int uho;

			public Test(int a){
				this.uho = a;
			}
			public int getUho() {
				return uho;
			}
		}

		//stream test->
		Stream<String> aaa = Stream.of("hoge","hage");
		System.out.println("aaa " + aaa.anyMatch(e -> e.equals("hoge")));
		List<Test> list = Arrays.asList(new Test(11),new Test(12),new Test(13));
		Map<String, Integer> bbb = list.stream().map(e->"aa" + e.getUho() + "aa").collect(Collectors.toMap(s->s,s->s.length()));
		//stream test<-

		Function<String, Integer> function = string -> Integer.parseInt(string);
		function.apply("12345678");

		for(String str : service){
			Service hoge = ServiceFactory.create(str);
			hoge.run();
		}

		for(String arg : args){
			System.out.println(arg);
		}
	}
}
