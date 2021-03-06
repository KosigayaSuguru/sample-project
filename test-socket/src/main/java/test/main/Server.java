package test.main;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server implements Runnable {

	Socket socket;
	// クローズ処理中かどうかのフラグ
	boolean closing = false;
	Logger log = LoggerFactory.getLogger(Server.class);

	public Server(Socket socket) {
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

					// あまり綺麗ではないけど、1byteずつ読み込んだ方が終端の処理がしやすい。。
					a = stream.read();
					log.debug("receive data:{}", a);

					bstream.write(a);

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

						// 受信確認の応答送信
						DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
						byte[] b = { 'r', 'e', 's', ' ', 'o', 'k', '\0', '\0' };
						outputStream.write(b);
						continue;
					}

				}
				bstream.close();

				close();
				log.debug("socket close");
				log.debug("end");
				break;

				// socket.close()以外の正規の手順を踏まずに接続が切れた場合、例外が吐かれるっぽい。。
				// ※例：readブロック中に相手がCtrl+Cでアプリを終わらせたりとか
				//
				// クローズ処理中に例外が出た場合、ログ出力は無視する
			} catch (Exception e) {
				if (!closing) {
					log.debug(e.getMessage(), e);
				}
				break;
			}

		}
		log.debug("receiver thread end");
	}

	public void close() {
		closing = true;

		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
