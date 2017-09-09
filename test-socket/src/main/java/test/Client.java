package test;

import java.io.DataOutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

	public static void main(String[] args) {

		Logger log = LoggerFactory.getLogger(Client.class);
		try {
			Socket socket = new Socket("localhost", 9999);
			log.debug("open socket");

			DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
			log.debug("getOutputstream");

			int sleep = 5000;
			log.debug("waiting...{}s", sleep / 1000);
			Thread.sleep(sleep);

			byte[] senddata = { 1, 1, 9, 2 };
			stream.write(senddata);
			log.debug("write");

			stream.flush();
			log.debug("flush");

			int cnt = 5;
			while (cnt > 0) {
				Thread.sleep(1000);
				log.debug(cnt + "");
				cnt--;
			}

			socket.close();
			log.debug("close");

		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
	}

}
