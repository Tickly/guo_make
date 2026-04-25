package com.taoguo.guo_make

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
    private val mainHandler = Handler(Looper.getMainLooper())

    /**
     * Activity 创建回调：展示按钮并发起固定小组件请求。
     *
     * @param savedInstanceState 输入：Activity 重建时系统提供的状态（可为空）。
     * @return 输出：无返回值。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_widget)

        val statusText = findViewById<TextView>(R.id.add_widget_status)
        val button = findViewById<Button>(R.id.add_widget_button)

        button.setOnClickListener {
            val result = tryRequestPinWidget()
            when (result) {
                PinWidgetResult.NotSupported -> {
                    statusText.setText(R.string.add_widget_not_supported)
                    Toast.makeText(this, R.string.add_widget_not_supported, Toast.LENGTH_LONG).show()
                }
                PinWidgetResult.RequestSent -> {
                    statusText.setText(R.string.add_widget_request_sent)
                    Toast.makeText(this, R.string.add_widget_request_sent, Toast.LENGTH_SHORT).show()

                    mainHandler.postDelayed(
                        {
                            statusText.setText(R.string.add_widget_no_prompt_hint)
                        },
                        4000L,
                    )
                }
                PinWidgetResult.Failed -> {
                    statusText.setText(R.string.add_widget_request_failed)
                    Toast.makeText(this, R.string.add_widget_request_failed, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    /**
     * 请求系统将日期小组件固定到桌面。
     *
     * @return 输出：本次请求的结果（不支持/已发起/失败）。
     */
    private fun tryRequestPinWidget(): PinWidgetResult {
        val appWidgetManager = getSystemService(AppWidgetManager::class.java) ?: return PinWidgetResult.Failed
        if (!appWidgetManager.isRequestPinAppWidgetSupported) return PinWidgetResult.NotSupported

        val provider = ComponentName(this, DateWidgetProvider::class.java)
        val callback = AddWidgetPinnedReceiver.createSuccessCallback(this)
        val ok = appWidgetManager.requestPinAppWidget(provider, null, callback)
        return if (ok) PinWidgetResult.RequestSent else PinWidgetResult.Failed
    }

    /**
     * 固定小组件请求的结果枚举。
     *
     * @return 输出：无返回值。
     */
    private enum class PinWidgetResult {
        NotSupported,
        RequestSent,
        Failed,
    }
}

