package xyz.tetnacle.lexingtonwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews

class BatteryWidget : AppWidgetProvider() {
    val instance: BatteryWidget?
        get() {
            if (mInstance == null) {
                mInstance = BatteryWidget()
            }
            return mInstance
        }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.d(TAG, "onUpdate: aaaaaa")
        val intent = Intent(context, BatteryService::class.java)
        context.startService(intent)
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
        val intent = Intent(context, BatteryService::class.java)
        context.stopService(intent)
    }

    companion object {
        const val TAG = "BatteryAppWidgetProvider"
        var mInstance: BatteryWidget? = null
        fun notifyChange(context: Context, m: Int) {
            val thisWidget = ComponentName(
                context,
                BatteryWidget::class.java
            )
            val appManager = AppWidgetManager.getInstance(context)
            val views = RemoteViews(
                context.packageName,
                R.layout.new_app_widget
            )
            val images = arrayOf(R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5)
            when (m) {
                in 1..20 -> {
                    views.setImageViewResource(R.id.widget_image, images[0])
                }
                in 21..40 -> {
                    views.setImageViewResource(R.id.widget_image, images[1])
                }
                in 41..60 -> {
                    views.setImageViewResource(R.id.widget_image, images[2])
                }
                in 61..80 -> {
                    views.setImageViewResource(R.id.widget_image, images[3])
                }
                in 81..100 -> {
                    views.setImageViewResource(R.id.widget_image, images[4])
                }
            }
            appManager.updateAppWidget(thisWidget, views)
        }
    }
}
