package com.noactivity.androidservicetest

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity()
{

    /**
     * 初回処理
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)

        // サービス起動
        val intent = GetMyService()
        startService(intent)

    }

    /**
     * 終了処理
     */
    override fun onDestroy()
    {

        super.onDestroy()

        val intent = GetMyService()
        stopService(intent)

    }

    /**
     * サービスのIntentを取得
     */
    private fun GetMyService(): Intent
    {
        return Intent(this@MainActivity, MyService::class.java)
    }

}