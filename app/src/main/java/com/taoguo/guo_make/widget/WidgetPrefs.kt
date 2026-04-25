package com.taoguo.guo_make.widget

import android.content.Context

/**
 * 小组件配置存储工具：用于按 appWidgetId 保存/读取/删除每个小组件实例的配置。
 *
 * @return 输出：无返回值。
 */
object WidgetPrefs {
    private const val PREFS_NAME = "widget_prefs"
    private const val KEY_TEXT_PREFIX = "text_"

    /**
     * 保存文本小组件的展示内容。
     *
     * @param context 输入：上下文。
     * @param appWidgetId 输入：小组件实例 id。
     * @param text 输入：需要展示的文本内容。
     * @return 输出：无返回值。
     */
    fun saveText(context: Context, appWidgetId: Int, text: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_TEXT_PREFIX + appWidgetId, text).apply()
    }

    /**
     * 读取文本小组件的展示内容。
     *
     * @param context 输入：上下文。
     * @param appWidgetId 输入：小组件实例 id。
     * @return 输出：读取到的文本内容；若未配置则返回空字符串。
     */
    fun loadText(context: Context, appWidgetId: Int): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_TEXT_PREFIX + appWidgetId, "") ?: ""
    }

    /**
     * 删除某个小组件实例的配置，避免残留数据。
     *
     * @param context 输入：上下文。
     * @param appWidgetId 输入：小组件实例 id。
     * @return 输出：无返回值。
     */
    fun deleteText(context: Context, appWidgetId: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_TEXT_PREFIX + appWidgetId).apply()
    }
}

