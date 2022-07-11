package com.noactivity.androidservicetest

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService: Service()
{

    /**
     * 初期化
     */
    override fun onCreate()
    {
        super.onCreate()
    }

    /**
     * サービスをBindして実行する場合は{}内に処理を記述
     */
    override fun onBind(p0: Intent?): IBinder?
    {
        TODO("Not yet implemented")
    }

    /**
     * サービス開始
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {

        //super.onStartCommand(intent, flags, startId)

        /* バックグラウンドで行う処理を記述 */

        // エラー発生時、自動で再起動しない設定とする
        return START_NOT_STICKY
        // 自動再起動したい場合は以下をreturnする
        // START_STICKY             (インテント:null)
        // START_REDELIVER_INTENT   (インテント:直前に保持した値)

    }

    /**
     * サービス終了
     */
    override fun onDestroy()
    {
        super.onDestroy()
    }

}