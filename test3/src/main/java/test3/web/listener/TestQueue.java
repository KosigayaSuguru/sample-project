package test3.web.listener;

import java.util.concurrent.ArrayBlockingQueue;

@SuppressWarnings("serial")
public class TestQueue extends ArrayBlockingQueue<String> {

	private String queueName = "";

	public TestQueue(int size, String name) {
		super(size);
		queueName = name;
	}
}
