package test.送信者が受信者のreadより先にソケットをcloseする;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

	public static void main(String[] args) throws IOException {

		Logger log = LoggerFactory.getLogger(Server.class);

		ServerSocket s = new ServerSocket(9999);
		log.debug("server open");

		while (true) {
			try {
				log.debug("socket accept wait");
				Socket socket = s.accept();
				log.debug("socket accept");

				int sleep = 15000;
				log.debug("waiting...{}s", sleep / 1000);
				Thread.sleep(sleep);

				InputStream stream = socket.getInputStream();
				log.debug("getInputstream");

				int a = 0;
				ByteArrayOutputStream bstream = new ByteArrayOutputStream();
				while (true) {
					a = stream.read();
					log.debug("receive data:{}", a);

					bstream.write(a);

					// 終端文字が来てる時の処理
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

			} catch (Exception e) {
				log.debug(e.getMessage(), e);

			}
		}

	}

}
