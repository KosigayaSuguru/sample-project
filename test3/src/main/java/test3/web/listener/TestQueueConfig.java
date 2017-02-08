package test3.web.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TestQueueConfig {

	@Bean
	TestQueue testQueue() {
		TestQueue ret = new TestQueue(10, "TEST_QUEUE");
		return ret;
	}
}
