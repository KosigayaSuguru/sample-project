package test.送信者が受信者のreadブロック中にwriteとflushして即closeする;

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

			int sleep = 10000;
			log.debug("waiting...{}s", sleep / 1000);
			Thread.sleep(sleep);

			DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
			log.debug("getOutputstream");

			stream.write("あああああ".getBytes());
			stream.flush();
			socket.close();

		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
	}

}
