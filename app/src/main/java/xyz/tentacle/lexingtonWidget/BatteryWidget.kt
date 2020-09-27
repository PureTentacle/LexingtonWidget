package xyz.tentacle.lexingtonWidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews

class BatteryWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val intent = Intent(context, BatteryService::class.java)
        context.startService(intent)
        Log.d(TAG, "onUpdate: appWidgetIds" + appWidgetIds.size)
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.new_app_widget)
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onDisabled(context: Context) {
        Log.d(TAG, "onDisabled: Battery Widget Disabled")
        super.onDisabled(context)
        val intent = Intent(context, BatteryService::class.java)
        context.stopService(intent)
    }

    companion object {
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
            when (m) {
                in 1..20 -> {
                    views.setImageViewResource(R.id.widget_image, R.drawable.a1)
                }
                in 21..40 -> {
                    views.setImageViewResource(R.id.widget_image, R.drawable.a2)
                }
                in 41..60 -> {
                    views.setImageViewResource(R.id.widget_image, R.drawable.a3)
                }
                in 61..80 -> {
                    views.setImageViewResource(R.id.widget_image, R.drawable.a4)
                }
                in 81..100 -> {
                    views.setImageViewResource(R.id.widget_image, R.drawable.a5)
                }
            }
            appManager.updateAppWidget(thisWidget, views)
        }
    }
}
