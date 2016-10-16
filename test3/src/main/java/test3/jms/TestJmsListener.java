package test3.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import test3.bean.TestBean;

@Component
public class TestJmsListener {

	@JmsListener(destination = "jms/queue/TodoMessageQueue")
	public void receive(TestBean todo) {
		// omitted
		System.err.println("JMS TEST");
	}
}
