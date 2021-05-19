package app.murauchi.mirerun.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    //クイズを作る
    val quizLists: List<List<String>> = listOf(
        listOf("Androidコースのキャラクターの名前は？","ランディ","フィル","ドロイド","ランディ"),
        listOf("Androidアプリを開発する言語はどれ？","JavaScript","Kotlin","Swift","Kotlin"),
        listOf("ImageViewは、何を扱える要素？","文字","音声","画像","画像")
    )
    //クイズをシャッフルして変数に入れる
    val shuffledLists: List<List<String>> = quizLists.shuffled()
    //クイズ数をカウントする変数を作る
    var quizCount: Int = 0
    //正解の答えを入れる変数を作る
    var correctAnswer: String = ""
    //正解の回数を入れる変数を作る
    var correctCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        //関数を呼び出してクイズを表示
        showQuestion()

        //1つ目のボタンがタッチされたら
        answerButton1.setOnClickListener {
            //解答をチェック
            checkAnswer(answerButton1.text.toString())
        }
        answerButton2.setOnClickListener {
            //解答をチェック
            checkAnswer(answerButton2.text.toString())
        }
        answerButton3.setOnClickListener {
            //解答をチェック
            checkAnswer(answerButton3.text.toString())
        }
        //次に進むボタンがタップされたら
        nextButton.setOnClickListener {
            //現在のクイズ数と全問クイズ数が一致するか比較して
            if (quizCount == quizLists.size) {
                //一緒なら結果画面へ移動する準備
                val resultIntent: Intent = Intent(this, ResultActivity::class.java)
                //クイズ数をセット
                resultIntent.putExtra("QuizCount", quizLists.size)
                //正解数をセット
                resultIntent.putExtra("CorrectCount", quizLists.size)
                //結果画面に移動
                startActivity(resultIntent)

            } else {
                //一緒でなければ判定●×画像を非表示にする
                judgeImage.isVisible = false
                nextButton.isVisible = false
                //解答ボタンの有効にする
                answerButton1.isEnabled = true
                answerButton2.isEnabled = true
                answerButton3.isEnabled = true
                //正解表示をリセットする
                correctAnswerText.text = ""
                //クイズを表示する
                showQuestion()

            }
        }
    }

    //画面に表示するクイズを作る
    fun showQuestion() {
        //クイズを取り出す
        val question: List<String> = shuffledLists[quizCount]
        //クイズの中身を確認する
        //Log.d("debug", question.toString())
        //クイズをTextViewに反映する
        quizText.text = question[0]
        //クイズの選択肢を表示する
        answerButton1.text = question[1]
        answerButton2.text = question[2]
        answerButton3.text = question[3]
        //クイズの正しい答えをリセット
        correctAnswer = question[4]
    }

    //解答をチェック
    fun checkAnswer(answerText: String) {
        //タップされた解答と正しい解答を比べて
        if (answerText == correctAnswer) {
            //一緒だったら〇画像を反映
            judgeImage.setImageResource(R.drawable.maru_image)
            //正解した回数をカウント
            correctCount++
        } else {
            //違っていたら×画像を反映
            judgeImage.setImageResource(R.drawable.batu_image)
        }
        //判定画像を表示する
        showAnswer()
        //クイズ数をカウント
        quizCount++
    }

    //判定画像を表示する際のボタンを設定
    fun showAnswer() {
        //正解を表示する
        correctAnswerText.text = "正解：$correctAnswer"
        //判定画像を表示(●×)
        judgeImage.isVisible = true
        //次へボタンを表示
        nextButton.isVisible = true
        //解答ボタンを無効にする
        answerButton1.isEnabled = false
        answerButton2.isEnabled = false
        answerButton3.isEnabled = false
    }
}