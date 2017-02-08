package test3.web.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import test3.web.listener.TestQueue;

@RestController
public class ListenerController extends WebApplicationObjectSupport {

	private int key = 0;
	public static HashMap<String, TestQueue> responseQueueMapper = new HashMap<>();

	@Autowired
	TestQueue testQueue;

	@GetMapping("/ListenerTest")
	String listner() throws InterruptedException {

		ArrayList<String> keyList = new ArrayList<>();

		// キュー(testQueue)に投げっぱなしにする
		// TestListenerConfigで立ち上げたListenerがキューに値がputさせるのを待っている(立ちあげたリスナー数で流量が制限される)
		// Listenerが処理結果をここに返すすための応答キューを、ユニーク値(queueKey)をキーにしたマップのバリューに設定する
		// Listenerは処理結果をqueueKey毎の応答キューにputする
		for (int idx = 0; idx < 16; idx++) {
			String queueKey = "res" + key;
			keyList.add(queueKey);
			key++;
			TestQueue queue = new TestQueue(1, queueKey);
			responseQueueMapper.put(queueKey, queue);
			testQueue.put(queueKey);
		}

		System.out.println("finished request");

		// testQueueに投げっぱなしにされたリクエストが、TestListenerConfigで立ち上げたスレッド数で非同期に処理されて、
		// 処理結果をqueueKeyに対応した応答キューにputするので、それを取得していく
		// 処理結果を取得する順番はqueueKeyの登録順になる
		String ret = "";
		for (String queueKey : keyList) {
			TestQueue queue = responseQueueMapper.get(queueKey);
			String resQueueStr = queue.take();
			System.out.println("get:" + resQueueStr);
			ret += resQueueStr + "</br>";
			responseQueueMapper.remove(queueKey);
		}

		return ret;
	}
}
