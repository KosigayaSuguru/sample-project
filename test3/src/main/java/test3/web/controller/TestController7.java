package test3.web.controller;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.hornetq.jms.server.embedded.EmbeddedJMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
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
	// D:\java\hornetq-2.4.0.Final\config\stand-alone\non-clustered\hornetq-jms.xml
	// 内の queue で指定されているキューを使う必要がある
	// 先に /JmsTest へExpiryQueueへ メッセージを投げて、DLQを待ち受けする。
	// @JmsListener(destination = "ExpiryQueue")がメッセージを受け取って処理を始める。
	// 処理が終わると DLQ へメッセージを投げて、/JmsTestの方が処理始める。
	@RequestMapping("/JmsTest")
	public String jmsTest() throws JMSException {

		TestBean test = new TestBean();
		test.setId(a);
		test.setHoge("jms1 hoge" + a);
		test.setMoge("jms1 moge" + a++);

		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		accessor.setHeader("jms_priority", 5);
		Message<TestBean> testMessage = MessageBuilder.withPayload(test).setHeaders(accessor).build();

		jmsMessagingTemplate.convertAndSend("ExpiryQueue", testMessage);

		// javax.jms.Message message = jmsMessagingTemplate.getJmsTemplate().receive("DLQ");
		// javax.jms.Message message = jmsMessagingTemplate.getJmsTemplate().receiveSelected("DLQ", "priority > 3");
		Message<?> message = jmsMessagingTemplate.receive("DLQ");
		System.err.println("aaaaaaaaaaaaaaaaaaaaaa" + message.toString());
		System.err.println("aaaaaaaaaaaaaaaaaaaaaa" + message.getPayload().toString());

		return test.toString();
	}

	@JmsListener(destination = "ExpiryQueue")
	public void receive(Message<TestBean> message) {

		// omitted
		System.err.println("bbbbbbbbbbbbbbbbbbb" + message.getPayload().toString());

		// jmsMessagingTemplate.convertAndSend("DLQ", message);

		TestBean test = new TestBean();
		test.setId(a);
		test.setHoge("jms2 hoge" + a);
		test.setMoge("jms2 moge" + a++);

		MessageHeaderAccessor accessor = new MessageHeaderAccessor();
		accessor.setHeader("jms_priority", 5);
		Message<TestBean> testMessage = MessageBuilder.withPayload(test).setHeaders(accessor).build();
		jmsMessagingTemplate.convertAndSend("DLQ", testMessage);
	}

	@RequestMapping("/JmsTest2")
	public String jmsTest2() throws Exception {

		EmbeddedJMS hoge = new EmbeddedJMS();
		hoge.start();

		return test.toString();
	}
}
