package main.test4;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import bean.test4.BeanA;
import bean.test4.BeanB;
import conf.test4.AppConfig;
import impl.test4.RegistService;
import inter.test4.IfService;

public class Main {

	public static void main(String[] args) {

		try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {
			BeanA main1 = context.getBean(BeanA.class);
			System.out.println(main1.hoge1);
			BeanB main2 = context.getBean(BeanB.class);
			System.out.println(main2.hoge1);
			IfService tmp2 = context.getBean(RegistService.class);
			System.out.println(tmp2);
			tmp2.run();
			IfService tmp3 = context.getBean(RegistService.class);
			System.out.println(tmp3);
			tmp3.run();
		}
		System.out.println("end");
	}
}
