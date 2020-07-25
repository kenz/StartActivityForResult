package org.firespeed.androidactivityforresult

import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * 呼び出し元Activity
 */
class MainActivity : AppCompatActivity() {

    /**
     * 呼び出し先Activityで作成したカスタムコントラクトを使用する
     * @see SecondActivity
     */
    private val activityLauncher =
        registerForActivityResult(SecondActivity.ResultContracts()) {
            // 結果をStringで受け取ることができる
            findViewById<TextView>(R.id.result).text = it
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val number = findViewById<NumberPicker>(R.id.number)
        number.maxValue = 255
        findViewById<Button>(R.id.startActivityButton).setOnClickListener {
            // TypeSafeに値を渡すことができる
            activityLauncher.launch(number.value)
        }
    }
}