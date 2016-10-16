package test3.web.controller;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
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

	@Autowired
	CachingConnectionFactory cachingConnectionFactory;

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

	// HornetQの起動→の普通に起動するだけ:"D:\java\hornetq-2.4.0.Final\bin\run.bat"
	// D:\java\hornetq-2.4.0.Final\config\stand-alone\non-clustered\hornetq-jms.xml 内の queue で指定されているキューを使う必要がある
	// 先に /JmsTest2 へアクセスし、receiveを起動してから、/JmsTestをへアクセスすると、TestBeanの値が、/JmsTest2へ渡される
	@RequestMapping("/JmsTest")
	public String jmsTest() throws JMSException {

		TestBean test = new TestBean();
		test.setHoge("jms hoge");
		test.setMoge("jms moge");

		jmsMessagingTemplate.convertAndSend("ExpiryQueue", test);

		return test.toString();
	}

//	@JmsListener(destination = "testQueue")
//	public void receive(TestBean todo) {
//		// omitted
//		System.err.println("JMS TEST");
//	}

	@RequestMapping("/JmsTest2")
	public void receive(TestBean todo) {

		Message<?> message = jmsMessagingTemplate.receive("ExpiryQueue");

		// omitted
		System.err.println(message.toString());
	}
}
