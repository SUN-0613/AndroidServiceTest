package com.noactivity.androidservicetest

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService: Service()
{

    /**
     * 初期化
     */
    override fun onCreate() {
        super.onCreate()
    }

    /**
     * サービスをBindして実行する場合は{}内に処理を記述
     */
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    /**
     * サービス開始
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * サービス終了
     */
    override fun onDestroy() {
        super.onDestroy()
    }

}