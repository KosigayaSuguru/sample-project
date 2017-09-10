# socketまとめ
  
ざくっというと、

* close()するとTCP的にはFINが送信される。
* readブロック中に相手がclose()すると、read()が-1（終端応答）を返す。
* readブロック中に相手がclose()以外の方法で切断すると、例外が発生する。（Ctrl+Cで終了させるとか、closeしないままアプリが終わるとか等）
* write()は相手がclose()してもできる（例外にならない）。
* 通信相手の生存確認が必要な場合、read()してみてエラーになるかどうかで確認するのがいいような気がする

## 知識
↓接続確立時。
```
  TCP         0.0.0.0:9999           0.0.0.0:0              LISTENING
  TCP         127.0.0.1:9999         127.0.0.1:51122        ESTABLISHED
  TCP         127.0.0.1:51122        127.0.0.1:9999         ESTABLISHED
  TCP         [::]:9999              [::]:0                 LISTENING
```
↓片方がsocket.close()する。
```
  TCP         0.0.0.0:9999           0.0.0.0:0              LISTENING
  TCP         127.0.0.1:9999         127.0.0.1:51122        FIN_WAIT_2
  TCP         127.0.0.1:51122        127.0.0.1:9999         CLOSE_WAIT
  TCP         [::]:9999              [::]:0                 LISTENING
```
この状態から、close()していない方が、read() or write() すると、↓になる。
```
  TCP         0.0.0.0:9999           0.0.0.0:0              LISTENING
  TCP         [::]:9999              [::]:0                 LISTENING
```
ここから、さらにread() or write() すると、例外が発生する。  


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

## 受信者が送信者のwriteより先にソケットをcloseする3
  
CS→接続  
S→getInputStream  
S→socket.close  
C→getOutputsream  
C→write  
C→flush  
C→getInputStream  
C→read  
C→例外  
  
2の亜種で、2で送信者がflushした後、送信者がgetInputStreamとreadする。  
readのタイミングで例外が発生する。  

### 生ログ

```
2017-09-10 21:29:24 [main] DEBUG t.受.Server - server open
2017-09-10 21:29:24 [main] DEBUG t.受.Server - socket accept wait
2017-09-10 21:29:26 [main] DEBUG t.受.Server - socket accept
2017-09-10 21:29:26 [main] DEBUG t.受.Server - waiting...1s
2017-09-10 21:29:27 [main] DEBUG t.受.Client - open socket
2017-09-10 21:29:27 [main] DEBUG t.受.Client - waiting...5s
2017-09-10 21:29:28 [main] DEBUG t.受.Server - getInputstream
2017-09-10 21:29:28 [main] DEBUG t.受.Server - socket close
2017-09-10 21:29:28 [main] DEBUG t.受.Server - end
2017-09-10 21:29:28 [main] DEBUG t.受.Server - socket accept wait
2017-09-10 21:29:32 [main] DEBUG t.受.Client - getOutputstream
2017-09-10 21:29:32 [main] DEBUG t.受.Client - write
2017-09-10 21:29:32 [main] DEBUG t.受.Client - flush
2017-09-10 21:29:32 [main] DEBUG t.受.Client - getInputStream
2017-09-10 21:29:32 [main] DEBUG t.受.Client - read wait
2017-09-10 21:29:32 [main] DEBUG t.受.Client - Software caused connection abort: recv failed
java.net.SocketException: Software caused connection abort: recv failed
	at java.net.SocketInputStream.socketRead0(Native Method)
	at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
	at java.net.SocketInputStream.read(SocketInputStream.java:171)
	at java.net.SocketInputStream.read(SocketInputStream.java:141)
	at java.net.SocketInputStream.read(SocketInputStream.java:224)
	at test.受信者が送信者のwriteより先にソケットをcloseする3.Client.main(Client.java:37)
```

## 受信者が送信者のwriteより先にソケットをcloseする4
  
CS→接続  
S→getInputStream  
S→socket.close  
C→getInputStream  
C→read = -1  
C→close
  
3の送信者がflush無し版。writeのための処理をしないで、送信者がgetInputStreamとreadする。  
readのタイミングで例外が発生せず、普通に終端(-1)を受けてcloseしている。  

### 生ログ

```
2017-09-11 00:38:33 [main] DEBUG t.受.Server - socket accept
2017-09-11 00:38:33 [main] DEBUG t.受.Server - waiting...1s
2017-09-11 00:38:33 [main] DEBUG t.受.Client - open socket
2017-09-11 00:38:33 [main] DEBUG t.受.Client - waiting...5s
2017-09-11 00:38:34 [main] DEBUG t.受.Server - getInputstream
2017-09-11 00:38:34 [main] DEBUG t.受.Server - socket close
2017-09-11 00:38:34 [main] DEBUG t.受.Server - end
2017-09-11 00:38:34 [main] DEBUG t.受.Server - socket accept wait
2017-09-11 00:38:38 [main] DEBUG t.受.Client - getInputStream
2017-09-11 00:38:38 [main] DEBUG t.受.Client - read wait
2017-09-11 00:38:38 [main] DEBUG t.受.Client - close
```

## 受信者が送信者のwriteより先にソケットをcloseする5
  
CS→接続  
S→getInputStream  
S→socket.close  
C→getOutputsream  
C→write 1回目  
C→flush 1回目  
C→write 2回目  
C→flush 1回目  
C→例外  

3の送信者がflushした後、さらにwriteする版。この場合、2回目のwriteで例外が発生する。  
netstatを見ていると、  
1回目のwrite前
```
  TCP         0.0.0.0:9999           0.0.0.0:0              LISTENING
  TCP         127.0.0.1:9999         127.0.0.1:50685        FIN_WAIT_2
  TCP         127.0.0.1:50685        127.0.0.1:9999         CLOSE_WAIT
  TCP         [::]:9999              [::]:0                 LISTENING
```
↓ 1回目のwrite後
```
  TCP         0.0.0.0:9999           0.0.0.0:0              LISTENING
  TCP         [::]:9999              [::]:0                 LISTENING
```
となっている。  
これまでの動作から、FIN_WAIT_2, CLOSE_WAITの状態になっているところ(相手側がsocket.close()した状態)で、read もしくは write の処理を実行すると、接続が完全に切断され、次の read or write で例外が出ているのではないかと思われる。


### 生ログ

```
2017-09-11 01:52:08 [main] DEBUG t.受.Server - socket accept
2017-09-11 01:52:08 [main] DEBUG t.受.Server - waiting...1s
2017-09-11 01:52:08 [main] DEBUG t.受.Client - open socket
2017-09-11 01:52:08 [main] DEBUG t.受.Client - waiting...5s
2017-09-11 01:52:09 [main] DEBUG t.受.Server - getInputstream
2017-09-11 01:52:09 [main] DEBUG t.受.Server - socket close
2017-09-11 01:52:09 [main] DEBUG t.受.Server - end
2017-09-11 01:52:09 [main] DEBUG t.受.Server - socket accept wait
2017-09-11 01:52:13 [main] DEBUG t.受.Client - getOutputstream
2017-09-11 01:52:13 [main] DEBUG t.受.Client - write1
2017-09-11 01:52:13 [main] DEBUG t.受.Client - flush1
2017-09-11 01:52:13 [main] DEBUG t.受.Client - write2
2017-09-11 01:52:13 [main] DEBUG t.受.Client - Software caused connection abort: socket write error
java.net.SocketException: Software caused connection abort: socket write error
	at java.net.SocketOutputStream.socketWrite0(Native Method)
	at java.net.SocketOutputStream.socketWrite(SocketOutputStream.java:111)
	at java.net.SocketOutputStream.write(SocketOutputStream.java:155)
	at java.io.DataOutputStream.write(DataOutputStream.java:107)
	at java.io.FilterOutputStream.write(FilterOutputStream.java:97)
	at test.受信者が送信者のwriteより先にソケットをcloseする5.Client.main(Client.java:34)
```
