# テストメモ

## JUnitメモ

### @RunWithにSpringRunnerを指定した時のSpring起動回数

複数クラスに分かれてテストを実施しても、Springが起動するのは一度だけになる。

### JMockito

起動させるためには、

* JUnitより、JMockitoのライブラリを先に読む必要がある(pom内のdependencyの位置を変えるで良い)
* JDKを使う必要がある

参照

* [JMockit使い方メモ](https://qiita.com/opengl-8080/items/a49d4dae9067413ccdd6) ※大分詳しい
