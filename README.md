# TranslationApp
100programで作成した、入力された日本語を尊敬語・謙譲語・丁寧語に翻訳するAndroidアプリケーションです。

開発環境は、Android Studio のJavaです。

※精度としてはまだまだで、実用レベルではないです。

このアプリの翻訳にはOpenAIを用いているのですが、その代わりのAIを自作・搭載して、実用段階のものにしようと開発中です。
<br><br>
利用手順

1．OpenAIの登録をし、APIキーを取得する。

https://openai.com/

2.このプロジェクトをダウンロードまたはクローンし、AndroidStudioで開く。

3.このプロジェクトに、TranslationFragment.javaがある。そのファイルの以下の写真の印の場所にAPIキーを追加する。

![explanation1](https://user-images.githubusercontent.com/101786527/205429537-b29c732b-81b7-45a1-90b6-f971b05bfbc9.png)

![explanation2](https://user-images.githubusercontent.com/101786527/205429549-4e27b420-8976-4e63-a853-370dfd90d0fb.png)

[explanation3](https://user-images.githubusercontent.com/101786527/205429552-79e27bee-1e7b-4fe6-8c2a-6350617540ed.png)

4.実行し、翻訳したい言葉を画面上部に入力し、翻訳ボタンを押す。
