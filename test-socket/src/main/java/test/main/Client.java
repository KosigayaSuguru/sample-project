package test.main;

import java.io.DataOutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

	public static void main(String[] args) {

		int sleep = 0;
		Logger log = LoggerFactory.getLogger(Client.class);
		try {
			Socket socket = new Socket("localhost", 9999);
			log.debug("open socket");

			sleep = 10000;
			log.debug("waiting...{}s", sleep / 1000);
			Thread.sleep(sleep);

			DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
			log.debug("getOutputstream");

			byte[] b = { 1, 2, 3, 4, 5, 0, 0 };
			stream.write(b);

			sleep = 2000;
			log.debug("waiting...{}s", sleep / 1000);
			Thread.sleep(sleep);

			byte[] bb = { 3, 4, 5, 6, 7 };
			stream.write(bb);

			socket.close();
			log.debug("close");

		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
	}

}
