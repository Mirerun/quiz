package app.murauchi.mirerun.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //クイズ画面に遷移する準備をする
        val quizIntent: Intent = Intent(this, QuizActivity::class.java)

        //STARTボタンがタップされたら
        startButton.setOnClickListener {
            //クイズ画面に移動する
            startActivity(quizIntent)
        }
    }
}