package com.taoguo.guo_make.widget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import com.taoguo.guo_make.AddWidgetPinnedReceiver

/**
 * 一键添加桌面小部件的工具类：封装 requestPinAppWidget 的调用与结果。
 *
 * @return 输出：无返回值。
 */
object PinWidgetHelper {
    /**
     * 一键添加请求结果。
     */
    enum class PinResult {
        NotSupported,
        RequestSent,
        Failed,
    }

    /**
     * 尝试向系统桌面请求固定指定 Provider 的小部件。
     *
     * @param context 输入：上下文。
     * @param providerClass 输入：AppWidgetProvider 的 class（例如 DateWidgetProvider / TextWidgetProvider）。
     * @return 输出：本次请求结果（不支持/已发起/失败）。
     */
    fun requestPin(context: Context, providerClass: Class<out Any>): PinResult {
        val manager = AppWidgetManager.getInstance(context) ?: return PinResult.Failed
        if (!manager.isRequestPinAppWidgetSupported) return PinResult.NotSupported

        val provider = ComponentName(context, providerClass)
        val callback = AddWidgetPinnedReceiver.createSuccessCallback(context)
        val ok = manager.requestPinAppWidget(provider, null, callback)
        return if (ok) PinResult.RequestSent else PinResult.Failed
    }
}

