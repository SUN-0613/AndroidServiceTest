package com.noactivity.androidservicetest

import android.app.Instrumentation
import android.app.Service
import android.app.UiAutomation
import android.content.Context
import android.content.Intent
import android.hardware.input.InputManager
import android.os.IBinder
import android.os.SystemClock
import android.view.InputDevice
import android.view.InputEvent
import android.view.MotionEvent
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.sql.DriverManager.println

/**
 * サービスクラス
 * https://qiita.com/b150005/items/bc7054a520d4b858dc0f
 * http://memento-mori-blog.com/kotlin-input-method-manager/
 * https://github.com/suhas-kotagal/my-app/blob/8ff12d6e816756248ee0a105e7ef1f2b6e6df6c0/app/src/main/java/com/logitech/integration/test/helpers/common/TestHelper.kt
 */
class MyService: Service()
{

    private var isRun: Boolean = true

    /**
     * 初期化
     */
    override fun onCreate()
    {

        println("onCreate実行")

        super.onCreate()
        //startService(Intent(this, MyService::class.java))
    }

    /**
     * サービスをBindして実行する場合は{}内に処理を記述
     */
    override fun onBind(p0: Intent?): IBinder?
    {
        println("onBind実行")

        return null
    }

    /**
     * サービス開始
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {

        println("onStartCommand実行")

        //super.onStartCommand(intent, flags, startId)

        /* バックグラウンドで行う処理を記述 */
        Run()

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

        isRun = false

        super.onDestroy()

    }

    /**
     * 実行処理
     */
    private fun Run()
    {

        println("Run:Start実行")

        // 10秒間隔でTapイベントを発行する
        var raiseEvent = SystemClock.uptimeMillis() + 1000

        while (isRun)
        {

            val ms = SystemClock.uptimeMillis()

            if (ms >= raiseEvent)
            {

                println("Run実行")

                sendTapEvent()

                raiseEvent = SystemClock.uptimeMillis() + 1000

            }

        }

    }

    /**
     * Tapイベントを発行する
     */
    private fun sendTapEvent()
    {

        var pointX: Float = 500f
        var pointY: Float = 500f

        val downTime = SystemClock.uptimeMillis()
        val eventTime = SystemClock.uptimeMillis() + 1000

        var event1 = MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_DOWN, pointX, pointY, 0)
        var event2 = MotionEvent.obtain(eventTime + 100, eventTime + 2000, MotionEvent.ACTION_UP, pointX, pointY, 0)

        event1.source = InputDevice.SOURCE_TOUCHSCREEN
        event2.source = InputDevice.SOURCE_TOUCHSCREEN

        injectInputEvent(event1)
        injectInputEvent(event2)

    }

    /**
     * 入力イベント
     */
    private fun injectInputEvent(event: InputEvent)
    {

        try
        {

            /*
            val instrumentation = Instrumentation()
            val ui = instrumentation.uiAutomation
            ui.injectInputEvent(event, false)
             */

            /*
            val manager = MainActivity.Instance?.getSystemService(Context.INPUT_METHOD_SERVICE)

            if (manager != null)
            {

                val method = manager!!.javaClass.getMethod("injectInputEvent", InputEvent::class.java, Integer.TYPE)
                method.invoke(manager!!, event, 0)
            }

             */

            //InputManager.class.getDeclaredMethod("injectInputEvent", new Class[]{InputEvent.class, int.class})

            val getMethod = InputManager::class.java.getDeclaredMethod("getInstance")
            val manager = getMethod.invoke(null)

            val method = InputManager::class.java.getDeclaredMethod(
                "injectInputEvent",
                InputEvent::class.java,
                Int::class.javaPrimitiveType)

            /*
            val manager = applicationContext.getSystemService(Context.INPUT_SERVICE) as InputManager
            var method: Method = InputManager::class.java.getMethod(
                "injectInputEvent",
                InputEvent::class.java,
                Int::class.javaPrimitiveType
            )
            */

            method.invoke(manager, event, 2)

        }
        catch (e: NoSuchMethodException)
        {
            e.printStackTrace()
        }
        catch (e: IllegalAccessException)
        {
            e.printStackTrace()
        }
        catch (e: IllegalArgumentException)
        {
            e.printStackTrace()
        }
        catch (e: InvocationTargetException)
        {
            e.printStackTrace()
        }

    }

}