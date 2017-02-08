package test3.web.listener;

import test3.web.controller.ListenerController;

public class TestListener extends Thread {

	private String listenerName = "";
	private int cnt = 0;
	private TestQueue myQueue;

	public TestListener(String name, TestQueue q) {
		listenerName = name;
		myQueue = q;
	}

	@Override
	public void run() {

		while (true) {
			try {
				String queueKey = myQueue.take();

				TestQueue responseQueue = ListenerController.responseQueueMapper.get(queueKey);

				Thread.sleep(2000);

				System.out.println(getClass() + "," + listenerName + "," + "get_message " + cnt++ + " -> " + queueKey);

				responseQueue.put("ListenerReturn:" + queueKey);

			} catch (InterruptedException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}
	}
}
