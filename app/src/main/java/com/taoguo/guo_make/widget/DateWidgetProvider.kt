package com.taoguo.guo_make.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.taoguo.guo_make.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * 桌面小组件 Provider：展示当前年月日。
 *
 * @return 输出：无返回值。
 */
class DateWidgetProvider : AppWidgetProvider() {

    /**
     * 小组件需要更新时触发（例如添加到桌面、周期刷新、手动刷新等）。
     *
     * @param context 输入：应用上下文。
     * @param appWidgetManager 输入：小组件管理器，用于更新视图。
     * @param appWidgetIds 输入：本 Provider 对应的所有小组件实例 ID。
     * @return 输出：无返回值。
     */
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_date)
            views.setTextViewText(R.id.widget_date_text, formatToday())
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    /**
     * 获取“今天”的日期字符串。
     *
     * @return 输出：格式为 `yyyy-MM-dd` 的日期字符串，例如 `2026-04-24`。
     */
    private fun formatToday(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.now().format(formatter)
    }
}

