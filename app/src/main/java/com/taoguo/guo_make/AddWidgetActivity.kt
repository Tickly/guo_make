package com.taoguo.guo_make

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taoguo.guo_make.widget.DateWidgetProvider

/**
 * “添加小部件”快捷入口。
 *
 * 通过 App Shortcuts（桌面长按应用图标菜单）触发，调用系统的固定小组件能力，将日期小组件添加到桌面。
 *
 * @param savedInstanceState 输入：Activity 重建时系统提供的状态（可为空）。
 * @return 输出：无返回值。
 */
class AddWidgetActivity : AppCompatActivity() {

    /**
     * Activity 创建回调：尝试发起固定小组件请求，并立即结束当前页面。
     *
     * @param savedInstanceState 输入：Activity 重建时系统提供的状态（可为空）。
     * @return 输出：无返回值。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tryRequestPinWidget()
        finish()
    }

    /**
     * 请求系统将日期小组件固定到桌面。
     *
     * @return 输出：无返回值。
     */
    private fun tryRequestPinWidget() {
        val appWidgetManager = getSystemService(AppWidgetManager::class.java) ?: return
        if (!appWidgetManager.isRequestPinAppWidgetSupported) return

        val provider = ComponentName(this, DateWidgetProvider::class.java)
        appWidgetManager.requestPinAppWidget(provider, null, null)
    }
}

