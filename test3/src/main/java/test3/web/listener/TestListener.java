package test3.web.listener;

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
				String hoge = myQueue.take();

				System.out.println(getClass() + "," + listenerName + "," + "get_message " + cnt++ + " -> " + hoge);

			} catch (InterruptedException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}
	}
}
