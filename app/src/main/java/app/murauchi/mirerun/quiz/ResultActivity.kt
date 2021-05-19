package app.murauchi.mirerun.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //クイズ画面からクイズ数を受け取る
        val quizCount: Int = intent.getIntExtra("QuizCount",0)
        //クイズ数をTextViewに反映
        quizCountText.text = "$quizCount 門中..."

        //正解数を受け取る
        val correctCount: Int = intent.getIntExtra("CorrectCount",0)
        //正解数をTextViewに反映
        correctCountText.text = correctCount.toString()

        //もう一回ボタンがタップされたら
        againButton.setOnClickListener {
            //クイズ画面へ移動する準備
            val quizIntent: Intent = Intent(this, QuizActivity::class.java)
            //クイズ画面に移動する
            startActivity(quizIntent)
        }
    }
}