# TranslationApp
100programで制作した、入力された日本語を尊敬語・謙譲語・丁寧語に翻訳するAndroidアプリケーションです。

開発環境は、Android Studio のJavaです。

※精度としてはまだまだで、実用レベルではないです。

※起動画面に保存のタブがありますが、今後追加予定のためまだ実装していないです。

このアプリは現在も開発を続けています。

UIの改善に加え、このアプリの翻訳機能で利用しているOpenAIに代わるAIを自作して搭載しようと試みています。
<br><br>
利用手順

1．OpenAIの登録をし、APIキーを取得する。

https://openai.com/

2．このプロジェクトをダウンロードまたはクローンし、AndroidStudioで開く。

3．このプロジェクトに、TranslationFragment.javaがある。<br>
そのファイルの以下の写真の印のカーソル位置に　　　　*Bearer APIキー*　　　　を追加する。

![explanation1](https://user-images.githubusercontent.com/101786527/205429806-418ba38b-52a6-40be-a9b3-71ba50c11e1a.png)

![explanation2](https://user-images.githubusercontent.com/101786527/205429810-c06efce0-db32-4e94-9620-02be64da3c29.png)

![explanation3](https://user-images.githubusercontent.com/101786527/205429815-9ce85233-02d7-4c4b-bfb8-f49809a82714.png)


4．実行後、翻訳したい言葉を起動画面の入力欄に入力し、翻訳ボタンを押す。
