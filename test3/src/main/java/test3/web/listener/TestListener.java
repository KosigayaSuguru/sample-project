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

				// 現状、アプリが停止する時以外、割り込みはないので例外処理は不要
				// ※ExecutorService(tTestListenerConfig.listenerShutdown())からshutdownNow()された場合にしかこない
			} catch (InterruptedException e1) {
			}
		}
	}
}
