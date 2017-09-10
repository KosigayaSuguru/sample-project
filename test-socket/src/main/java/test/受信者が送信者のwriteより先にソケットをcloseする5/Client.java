package test.受信者が送信者のwriteより先にソケットをcloseする5;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

	public static void main(String[] args) {

		Logger log = LoggerFactory.getLogger(Client.class);
		try {
			Socket socket = new Socket("localhost", 9999);
			log.debug("open socket");

			int sleep = 5000;
			log.debug("waiting...{}s", sleep / 1000);
			Thread.sleep(sleep);

			DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
			log.debug("getOutputstream");

			byte[] senddata = { 1, 1, 9, 2 };
			log.debug("write1");
			stream.write(senddata);

			log.debug("flush1");
			stream.flush();

			log.debug("write2");
			stream.write(senddata);

			log.debug("flush2");
			stream.flush();

			log.debug("getInputStream");
			InputStream inputStream = socket.getInputStream();
			int dd = 0;
			log.debug("read wait");
			while ((dd = inputStream.read()) > -1) {
				log.debug("{}", dd);
			}

			socket.close();
			log.debug("close");

		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
	}

}
