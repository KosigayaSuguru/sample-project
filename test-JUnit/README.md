# テストメモ

## JUnitメモ

### @RunWithにSpringRunnerを指定した時のSpring起動回数

複数クラスに分かれてテストを実施しても、Springが起動するのは一度だけになる。

### JMockito

起動させるためには、

* JUnitより、JMockitoのライブラリを先に読む必要がある(pom内のdependencyの位置を変えるで良い)
* JDKを使う必要がある

注意点

* @Mockedしたインスタンスがある場合、以降、該当インスタンスと同じクラスをnewするところが全部モックインスタンスになる。
* Alt+Shift+X T でJUnitをメソッドだけ実行させようとするとエラー出ることがあるので、classから実行させる。
* JUnitのビューから、メソッドを選択して実行する場合はセーフ（細かい原理不明）。
* Verificationsはちゃんと処理を実行した後に記述する必要がある。
* "JMockitoTests.モック化される範囲を確認する" を実行して、モック化インスタンスと、普通のインスタンスの参照関係をみると若干理解が深まるかも。。

参照

* [JMockit使い方メモ](https://qiita.com/opengl-8080/items/a49d4dae9067413ccdd6) ※大分詳しい
