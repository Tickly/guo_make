package com.taoguo.guo_make.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.taoguo.guo_make.R

/**
 * 文本小组件 Provider：展示用户自定义的一段文本（可为纯数字）。
 *
 * @return 输出：无返回值。
 */
class TextWidgetProvider : AppWidgetProvider() {

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
            updateOne(context, appWidgetManager, appWidgetId)
        }
    }

    /**
     * 删除小组件实例时触发：清理对应 appWidgetId 的存储配置。
     *
     * @param context 输入：应用上下文。
     * @param appWidgetIds 输入：被删除的小组件实例 ID 列表。
     * @return 输出：无返回值。
     */
    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            WidgetPrefs.deleteText(context, appWidgetId)
        }
    }

    /**
     * 更新某一个文本小组件实例的 RemoteViews。
     *
     * @param context 输入：上下文。
     * @param appWidgetManager 输入：小组件管理器。
     * @param appWidgetId 输入：要更新的小组件实例 ID。
     * @return 输出：无返回值。
     */
    private fun updateOne(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val text = WidgetPrefs.loadText(context, appWidgetId).ifBlank {
            context.getString(R.string.text_widget_default_text)
        }
        val theme = TextWidgetTheme.fromId(WidgetPrefs.loadThemeId(context, appWidgetId))
        val views = RemoteViews(context.packageName, R.layout.widget_text)
        views.setInt(R.id.widget_text_root, "setBackgroundResource", theme.backgroundRes)
        views.setTextViewText(R.id.widget_text_value, text)
        views.setTextColor(R.id.widget_text_value, theme.textColor)

        val editIntent = Intent(context, TextWidgetConfigureActivity::class.java).apply {
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        val editPendingIntent = PendingIntent.getActivity(
            context,
            appWidgetId,
            editIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )
        views.setOnClickPendingIntent(R.id.widget_text_value, editPendingIntent)

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}

