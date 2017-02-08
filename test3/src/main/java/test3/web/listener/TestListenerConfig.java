package test3.web.listener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationObjectSupport;

@Component
public class TestListenerConfig extends WebApplicationObjectSupport {

	ExecutorService threadPool;

	@Bean
	TestListener testListener() {
		ApplicationContext appContext = getApplicationContext();
		TestListener ret = new TestListener("TestListner", appContext.getBean("testQueue", TestQueue.class));
		return ret;
	}

	@PostConstruct
	void listnerStartup() {
		System.out.println(getClass() + ":listener startup");

		threadPool = Executors.newFixedThreadPool(10);

		// リスナーを３スレッド立ち上げる
		for (int cnt = 0; cnt < 3; cnt++) {
			threadPool.execute(testListener());
		}
	}

	@PreDestroy
	void listenerShutdown() {
		System.out.println(getClass() + ":listener shutdown");
	}
}
