package kd2048.co.jp.servicetest01

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import kotlin.math.absoluteValue

class kdReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        val str = p1?.getStringExtra("message")
        Log.d("kd>", "string " + str)

        /**
         * コンテキストp0がMainActivityならp0をMainActivityにキャストする
         * kotlinのキャストは if (p0 is MainActivity) を実行した時点でp0が
         * 自動的にMainActivityにキャストされる（これをスマートキャストという）
        */
        if (p0 is MainActivity) {
            val text = p0.findViewById<TextView>(R.id.textCount)
            text.setText(str)

            val progress = p0.findViewById<ProgressBar>(R.id.progressBar)
            val valu = str?.toInt()

            /**
             * setProgressはjavaのプリミティブ型の数値を受け取る
             * kotlinはInt?のときはnullチェックをしないと、プリミティブ型に
             * 変換ができずに、エラーになる
             * 誤: progress.setProgress(valu)
             * 正: if (valu != null) {progress.setProgress(valu)}
             *  if (valu != null) {
             *      progress.setProgress(valu)
             *  }
             * こんな書き方もできる
             * ?:の左辺がnullなら0を代入
             * progress.setProgress(valu ?: 0)
            */
            progress.setProgress(valu ?: 0)
        }
    }
}