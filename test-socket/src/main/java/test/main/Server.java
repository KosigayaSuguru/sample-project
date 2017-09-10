package test.main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

	public static void main(String[] args) throws IOException {

		Logger log = LoggerFactory.getLogger(Server.class);
		ExecutorService threadPool = Executors.newFixedThreadPool(10, new ServerThreadFactory());

		ServerSocket s = new ServerSocket(9999);
		log.debug("server open");

		while (true) {
			try {
				log.debug("socket accept wait");
				Socket socket = s.accept();
				log.debug("socket accept");

				threadPool.execute(new Receiver(socket));

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

	public static class Receiver implements Runnable {

		Socket socket;
		Logger log = LoggerFactory.getLogger(Receiver.class);

		public Receiver(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {

			while (true) {

				try {
					InputStream stream = socket.getInputStream();
					log.debug("getInputstream");

					int a = 0;
					int[] check = { 0, 0 };
					ByteArrayOutputStream bstream = new ByteArrayOutputStream();
					log.debug("start read wait");
					while (true) {
						a = stream.read();
						log.debug("receive data:{}", a);

						bstream.write(a);

						// 電文の区切りのチェック(2連続で0が送信されたらそこが区切り)
						check[0] = check[1];
						check[1] = a;
						if (check[0] == 0 && check[1] == 0) {

							StringBuilder ss = new StringBuilder();
							for (byte b : bstream.toByteArray()) {
								ss.append(String.format("[%2d]", b));
							}
							bstream.reset();
							log.debug("receive part of data:{}", ss);
							continue;
						}

						// 終端が来てる時の処理
						if (a == -1) {
							StringBuilder ss = new StringBuilder();
							for (byte b : bstream.toByteArray()) {
								ss.append(String.format("[%2d]", b));
							}
							log.debug("receive all data:{}", ss);

							// 終端のみが来ている場合、相手にクローズされているのでループを抜ける
							if (bstream.size() == 1) {
								break;
							}
							bstream.reset();
						}
					}
					bstream.close();

					socket.close();
					log.debug("socket close");
					log.debug("end");
					break;

				} catch (Exception e) {
					log.debug(e.getMessage(), e);
				}

			}
			log.debug("receiver thread end");
		}
	}
}
