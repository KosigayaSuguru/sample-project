package test.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerMain {

	public static void main(String[] args) throws IOException {

		Logger log = LoggerFactory.getLogger(ServerMain.class);
		ExecutorService threadPool = Executors.newFixedThreadPool(10, new ServerThreadFactory());

		ServerSocket s = new ServerSocket(9999);
		log.debug("server open");

		while (true) {
			try {
				log.debug("socket accept wait");
				Socket socket = s.accept();
				log.debug("socket accept");

				threadPool.execute(new Server(socket));

			} catch (Exception e) {
				log.debug(e.getMessage(), e);

			}
		}
	}

	public static class ServerThreadFactory implements ThreadFactory {

		int num = 0;

		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, String.format("Receiver-%d", num++));
		}
	}

}
