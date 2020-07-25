package org.firespeed.androidactivityforresult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity

/**
 * 呼び出され先Activity
 */
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val inputValue = intent.getIntExtra(INPUT_NUMBER, 0)
        val inputView = findViewById<TextView>(R.id.inputView)
        inputView.text = inputValue.toString()
        val returnEdit = findViewById<EditText>(R.id.returnEdit)
        val finishButton = findViewById<Button>(R.id.finishButton)

        finishButton.setOnClickListener {
            // 呼び出し元Activityに結果を返す
            intent.putExtra(OUTPUT_STRING, returnEdit.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    /**
     * このActivityのIn/Out情報をカスタムコントラクトに定義する
     * <Input, Output> ここではIntを受け取り Stringを返すようにしている。
     * 独自のクラスを指定することもできる
     */
    class ResultContracts : ActivityResultContract<Int, String>() {

        /**
         * InputからこのActivityを起動するために必要なIntentを作る
         */
        override fun createIntent(context: Context, input: Int): Intent =
            Intent(context, SecondActivity::class.java).apply {
                putExtra(INPUT_NUMBER, input)
            }

        /**
         * ResultのIntentをこのActivityのOutputに変換する
         * @param resultCode ActivityのResultCode
         * @param intent ResultのIntent Nullableだよ
         */
        override fun parseResult(resultCode: Int, intent: Intent?): String {
            return intent?.getStringExtra(OUTPUT_STRING) ?: ""
        }
    }

    companion object {
        // Extraのキー値はSecondActivityとResultContracts内で閉じているのでPrivateにできる
        private const val INPUT_NUMBER = "INPUT_NUMBER"
        private const val OUTPUT_STRING = "OUTPUT_STRING"
    }
}

