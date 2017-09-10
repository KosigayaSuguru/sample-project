package test.main;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
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

			InputStream inputStream = socket.getInputStream();

			// 受信確認電文の受信
			int in = 0;
			int[] check = { 0, 0 };
			log.debug("start read wait");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while ((in = inputStream.read()) > -1) {
				baos.write(in);
				log.debug("receive data:{}", in);

				// 電文の区切りのチェック(2連続で0が送信されたらそこが区切り)
				check[0] = check[1];
				check[1] = in;
				if (check[0] == 0 && check[1] == 0) {
					log.debug("receive all data:{}", baos.toString());
					break;
				}

				// 終端が来てる時の処理
				if (in == -1) {
					StringBuilder ss = new StringBuilder();
					for (byte c : baos.toByteArray()) {
						ss.append(String.format("[%2d]", c));
					}
					log.debug("receive all data:{}", ss);

					// 終端のみが来ている場合、相手にクローズされているのでループを抜ける
					if (baos.size() == 1) {
						break;
					}
					baos.reset();
				}
			}

			// sleep = 2000;
			// log.debug("waiting...{}s", sleep / 1000);
			// Thread.sleep(sleep);
			//
			// byte[] bb = { 3, 4, 5, 6, 7 };
			// stream.write(bb);

			socket.close();
			log.debug("socket close");

		} catch (Exception e) {
			log.debug(e.getMessage(), e);
		}
	}

}
