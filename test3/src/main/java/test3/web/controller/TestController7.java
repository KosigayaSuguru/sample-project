package test3.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.bean.TestBean;
import test3.bean.TestScopeBean;

@RestController
public class TestController7 extends WebApplicationObjectSupport {

	int a = 0;

	@Autowired
	TestScopeBean test;

	@Autowired
	JmsMessagingTemplate jmsMessagingTemplate;

	// SingletonなBeanにrequestスコープのBeanをDIする
	// ※実装はTestScopeBean参照
	// ※値はちゃんとリクエスト毎になるが、デバッガから中身が見れないので注意
	@RequestMapping("/BeanTest")
	public String myBatisTest() {

		System.out.println(test.toString());
		test.setHoge("hogehogehoge" + a++);
		test.setMoge("mogemogemoge" + a++);
		System.out.println(test.toString());
		return test.toString();
	}

	@RequestMapping("/JmsTest")
	public String jmsTest() {

		TestBean test = new TestBean();
		test.setHoge("jms hoge");
		test.setMoge("jms moge");

		jmsMessagingTemplate.convertAndSend("jms/queue/TodoMessageQueue", test);

		return test.toString();
	}
}
