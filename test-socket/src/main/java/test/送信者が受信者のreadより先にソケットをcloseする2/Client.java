package test.送信者が受信者のreadより先にソケットをcloseする2;

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

			socket.close();
			log.debug("close");

		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
	}

}
