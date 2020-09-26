package xyz.tetnacle.lexingtonwidget

import android.app.Service
import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews

const val TAG = "DEBUG"

class BatteryService : Service() {
    //必须使用广播来得到电量的值
    private var batteryServicer: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // TODO Auto-generated method stub
            val action = intent.action
            if (action == Intent.ACTION_BATTERY_CHANGED) {
                val level = intent.getIntExtra("level", 0)
                val scale = intent.getIntExtra("scale", 100)
                val levelN = level * 100 / scale
                BatteryWidget.notifyChange(context, levelN)
            }
        }
    }

    override fun onBind(arg0: Intent): IBinder? {
        // TODO Auto-generated method stub
        return null
    }

    override fun onStart(intent: Intent, startId: Int) {
        Log.d(TAG, "onStart: ")
        val mIntentFilter = IntentFilter()
        mIntentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryServicer, mIntentFilter)
    }

    override fun onDestroy() {
        unregisterReceiver(batteryServicer)
        super.onDestroy()
    }
}

