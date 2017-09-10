# socketまとめ
  
## 送信者が受信者のreadより先にソケットをcloseする
  
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

## 受信者が送信者のwriteより先にソケットをcloseする
  
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
