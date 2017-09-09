package test;

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

				while (true) {
					a = stream.read();
					log.debug("receive data:{}", a);

					if (a == -1) {
						break;
					}
				}

				while (true) {
					a = stream.read();
					log.debug("receive data2:{}", a);

					if (a == -1) {
						break;
					}
				}

				socket.close();
				log.debug("socket close");
				log.debug("end");

			} catch (Exception e) {
				log.debug(e.getMessage(), e);

			}
		}

	}

}
