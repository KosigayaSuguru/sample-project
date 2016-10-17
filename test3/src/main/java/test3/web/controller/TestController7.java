package test3.web.controller;

import javax.jms.Connection;
import javax.jms.JMSException;

import test3.jms.HornetQClient;

import org.hornetq.jms.server.embedded.EmbeddedJMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
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
	public String myBatisTest() throws Exception {

		System.out.println(test.toString());
		test.setHoge("hogehogehoge" + a++);
		test.setMoge("mogemogemoge" + a++);
		System.out.println(test.toString());
		return test.toString();
	}

	// HornetQの起動→の普通に起動するだけ:"D:\java\hornetq-2.4.0.Final\bin\run.bat"
	// D:\java\hornetq-2.4.0.Final\config\stand-alone\non-clustered\hornetq-jms.xml 内の queue で指定されているキューを使う必要がある
	// 先に /JmsTest へExpiryQueueへ メッセージを投げて、DLQを待ち受けする。
	// @JmsListener(destination = "ExpiryQueue")がメッセージを受け取って処理を始める。
	// 処理が終わると DLQ へメッセージを投げて、/JmsTestの方が処理始める。
	@RequestMapping("/JmsTest")
	public String jmsTest() throws JMSException {

		TestBean test = new TestBean();
		test.setHoge("jms hoge" + a++);
		test.setMoge("jms moge" + a++);

		jmsMessagingTemplate.convertAndSend("ExpiryQueue", test);

		Message<?> message = jmsMessagingTemplate.receive("DLQ");
		System.err.println("aaaaaaaaaaaaaaaaaaaaaa" + message.toString());

		return test.toString();
	}

	@JmsListener(destination = "ExpiryQueue")
	public void receive(TestBean message) {
		// omitted
		System.err.println("bbbbbbbbbbbbbbbbbbb" + message.toString());

		jmsMessagingTemplate.convertAndSend("DLQ", message);
	}

	@RequestMapping("/JmsTest2")
	public String jmsTest2() throws Exception {

		EmbeddedJMS hoge = new EmbeddedJMS();
		hoge.start();

		return test.toString();
	}
}
