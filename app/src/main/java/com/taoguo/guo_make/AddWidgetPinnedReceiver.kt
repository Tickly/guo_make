package com.taoguo.guo_make

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * 固定小组件成功后的回调接收器。
 *
 * 系统桌面完成“固定小组件”后，会通过 PendingIntent 回调到这里，用于给用户明确反馈。
 *
 * @return 输出：无返回值。
 */
class AddWidgetPinnedReceiver : BroadcastReceiver() {

    /**
     * 广播回调：收到后给出 Toast 提示。
     *
     * @param context 输入：应用上下文。
     * @param intent 输入：广播 Intent。
     * @return 输出：无返回值。
     */
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != ACTION_WIDGET_PINNED) return
        Toast.makeText(context, R.string.add_widget_pinned_success, Toast.LENGTH_LONG).show()
    }

    companion object {
        /**
         * 固定小组件成功后的广播 action。
         *
         * @return 输出：action 字符串。
         */
        const val ACTION_WIDGET_PINNED: String = "com.taoguo.guo_make.action.WIDGET_PINNED"

        /**
         * 创建固定小组件成功回调的 PendingIntent。
         *
         * @param context 输入：上下文。
         * @return 输出：用于 requestPinAppWidget 成功回调的 PendingIntent。
         */
        fun createSuccessCallback(context: Context): PendingIntent {
            val intent = Intent(context, AddWidgetPinnedReceiver::class.java).setAction(ACTION_WIDGET_PINNED)
            return PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
            )
        }
    }
}

