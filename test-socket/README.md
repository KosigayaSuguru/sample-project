# socketまとめ
  
ざくっというと、

* readブロック中に相手がcloseすると、readが-1（終端応答）を返す。
* readブロック中に相手がclose以外の方法で切断すると、例外が発生する。（Ctrl+Cで終了させるとか、closeしないままアプリが終わるとか等）
* writeは相手がcloseしてもできる（例外にならない）。

## 送信者が受信者のreadより先にソケットをcloseする1
  
クライアントが電文を送信した場合、  
  
CS→接続  
C→送信  
C→close  
S→getInputStream  
S→read  
S→streamから送信された電文を読み込み  
S→read  
S→即 -1 が返ってくる  
  
という形になる。  
  
### 生ログ

```
2017-09-10 03:22:10 [main] DEBUG test.Server - server open
2017-09-10 03:22:10 [main] DEBUG test.Server - socket accept wait
2017-09-10 03:22:12 [main] DEBUG test.Server - socket accept
2017-09-10 03:22:12 [main] DEBUG test.Server - waiting...15s
2017-09-10 03:22:12 [main] DEBUG test.Client - open socket
2017-09-10 03:22:12 [main] DEBUG test.Client - getOutputstream
2017-09-10 03:22:12 [main] DEBUG test.Client - waiting...5s
2017-09-10 03:22:17 [main] DEBUG test.Client - write
2017-09-10 03:22:17 [main] DEBUG test.Client - flush
2017-09-10 03:22:22 [main] DEBUG test.Client - close
2017-09-10 03:22:27 [main] DEBUG test.Server - getInputstream
2017-09-10 03:22:27 [main] DEBUG test.Server - receive data:1
2017-09-10 03:22:27 [main] DEBUG test.Server - receive data:1
2017-09-10 03:22:27 [main] DEBUG test.Server - receive data:9
2017-09-10 03:22:27 [main] DEBUG test.Server - receive data:2
2017-09-10 03:22:27 [main] DEBUG test.Server - receive data:-1
2017-09-10 03:22:27 [main] DEBUG test.Server - receive data2:-1
2017-09-10 03:22:27 [main] DEBUG test.Server - socket close
2017-09-10 03:22:27 [main] DEBUG test.Server - end
2017-09-10 03:22:27 [main] DEBUG test.Server - socket accept wait
```
## 送信者が受信者のreadより先にソケットをcloseする2
  
CS→接続  
C→close  
S→getInputStream  
S→read  
S→即 -1 が返ってくる  
  
送信者が接続して受信者のreadブロックが始まる前に、送信者が何も送信せずにcloseする。  
  
### 生ログ

```
2017-09-10 12:04:43 [main] DEBUG t.送.Server - socket accept
2017-09-10 12:04:43 [main] DEBUG t.送.Server - waiting...5s
2017-09-10 12:04:43 [main] DEBUG t.送.Client - open socket
2017-09-10 12:04:43 [main] DEBUG t.送.Client - getOutputstream
2017-09-10 12:04:43 [main] DEBUG t.送.Client - close
2017-09-10 12:04:48 [main] DEBUG t.送.Server - getInputstream
2017-09-10 12:04:48 [main] DEBUG t.送.Server - start read wait
2017-09-10 12:04:48 [main] DEBUG t.送.Server - receive data:-1
2017-09-10 12:04:48 [main] DEBUG t.送.Server - receive all data:[-1]
2017-09-10 12:04:48 [main] DEBUG t.送.Server - socket close
2017-09-10 12:04:48 [main] DEBUG t.送.Server - end
2017-09-10 12:04:48 [main] DEBUG t.送.Server - socket accept wait
```
## 送信者が受信者のreadより先にソケットをcloseする3
  
CS→接続  
S→getInputStream  
S→read  
C→close  
S→即 -1 が返ってくる  
  
送信者が接続して受信者のreadブロックが始まってから、送信者が何も送信せずにcloseする。  
  
### 生ログ

```
2017-09-10 11:57:05 [main] DEBUG t.送.Server - server open
2017-09-10 11:57:05 [main] DEBUG t.送.Server - socket accept wait
2017-09-10 11:57:07 [main] DEBUG t.送.Server - socket accept
2017-09-10 11:57:07 [main] DEBUG t.送.Server - waiting...5s
2017-09-10 11:57:07 [main] DEBUG t.送.Client - open socket
2017-09-10 11:57:07 [main] DEBUG t.送.Client - waiting...10s
2017-09-10 11:57:12 [main] DEBUG t.送.Server - getInputstream
2017-09-10 11:57:12 [main] DEBUG t.送.Server - start read wait
2017-09-10 11:57:17 [main] DEBUG t.送.Client - getOutputstream
2017-09-10 11:57:17 [main] DEBUG t.送.Client - close
2017-09-10 11:57:17 [main] DEBUG t.送.Server - receive data:-1
2017-09-10 11:57:17 [main] DEBUG t.送.Server - receive all data:[-1]
2017-09-10 11:57:17 [main] DEBUG t.送.Server - socket close
2017-09-10 11:57:17 [main] DEBUG t.送.Server - end
2017-09-10 11:57:17 [main] DEBUG t.送.Server - socket accept wait
```

## 送信者が受信者がreadブロックしている時に ctrl+c する
  
CS→接続  
S→getInputStream  
S→readブロック  
C→System.exit()  
S→例外(Connection reset)  
  
### 生ログ

```
2017-09-10 20:16:44 [main] DEBUG t.送.Server - server open
2017-09-10 20:16:44 [main] DEBUG t.送.Server - socket accept wait
2017-09-10 20:16:47 [main] DEBUG t.送.Server - socket accept
2017-09-10 20:16:47 [main] DEBUG t.送.Server - waiting...5s
2017-09-10 20:16:47 [main] DEBUG t.送.Client - open socket
2017-09-10 20:16:47 [main] DEBUG t.送.Client - waiting...10s
2017-09-10 20:16:52 [main] DEBUG t.送.Server - getInputstream
2017-09-10 20:16:52 [main] DEBUG t.送.Server - start read wait
2017-09-10 20:16:57 [main] DEBUG t.送.Client - getOutputstream
2017-09-10 20:16:57 [main] DEBUG t.送.Server - Connection reset
java.net.SocketException: Connection reset
	at java.net.SocketInputStream.read(SocketInputStream.java:210)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.net.SocketInputStream.read(SocketInputStream.java:224)
	at test.送信者が受信者のreadブロック中にCtrl_Cする.Server.main(Server.java:38)
2017-09-10 20:16:57 [main] DEBUG t.送.Server - socket accept wait
```

## 受信者が送信者のwriteより先にソケットをcloseする1
  
CS→接続  
S→getInputStream  
C→getOutputsream  
S→socket.close  
C→write  
C→flush  
C→close  
  
特に例外もエラー値応答も発生しない。お互いに何もなかったかのように何もない。  
  
### 生ログ

```
2017-09-10 11:32:37 [main] DEBUG t.受.Server - socket accept
2017-09-10 11:32:37 [main] DEBUG t.受.Server - waiting...1s
2017-09-10 11:32:37 [main] DEBUG t.受.Client - open socket
2017-09-10 11:32:37 [main] DEBUG t.受.Client - getOutputstream
2017-09-10 11:32:37 [main] DEBUG t.受.Client - waiting...5s
2017-09-10 11:32:38 [main] DEBUG t.受.Server - getInputstream
2017-09-10 11:32:38 [main] DEBUG t.受.Server - socket close
2017-09-10 11:32:38 [main] DEBUG t.受.Server - end
2017-09-10 11:32:38 [main] DEBUG t.受.Server - socket accept wait
2017-09-10 11:32:42 [main] DEBUG t.受.Client - write
2017-09-10 11:32:42 [main] DEBUG t.受.Client - flush
2017-09-10 11:32:42 [main] DEBUG t.受.Client - close
```

## 受信者が送信者のwriteより先にソケットをcloseする2
  
CS→接続  
S→getInputStream  
S→socket.close  
C→getOutputsream  
C→write  
C→flush  
C→close  
  
受信者がcloseしてからgetOutputStreamする。  
結果は1と一緒。  
  
### 生ログ

```
2017-09-10 11:38:09 [main] DEBUG t.受.Server - server open
2017-09-10 11:38:09 [main] DEBUG t.受.Server - socket accept wait
2017-09-10 11:38:12 [main] DEBUG t.受.Server - socket accept
2017-09-10 11:38:12 [main] DEBUG t.受.Server - waiting...1s
2017-09-10 11:38:12 [main] DEBUG t.受.Client - open socket
2017-09-10 11:38:12 [main] DEBUG t.受.Client - waiting...5s
2017-09-10 11:38:13 [main] DEBUG t.受.Server - getInputstream
2017-09-10 11:38:13 [main] DEBUG t.受.Server - socket close
2017-09-10 11:38:13 [main] DEBUG t.受.Server - end
2017-09-10 11:38:13 [main] DEBUG t.受.Server - socket accept wait
2017-09-10 11:38:17 [main] DEBUG t.受.Client - getOutputstream
2017-09-10 11:38:17 [main] DEBUG t.受.Client - write
2017-09-10 11:38:17 [main] DEBUG t.受.Client - flush
2017-09-10 11:38:17 [main] DEBUG t.受.Client - close
```
