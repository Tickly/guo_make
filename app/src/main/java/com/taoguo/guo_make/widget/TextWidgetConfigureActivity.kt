package com.taoguo.guo_make.widget

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.taoguo.guo_make.R

/**
 * 文本小组件的配置页：添加小组件时由系统自动拉起，用于输入并保存展示文本。
 *
 * @param savedInstanceState 输入：Activity 重建时系统提供的状态（可为空）。
 * @return 输出：无返回值。
 */
class TextWidgetConfigureActivity : AppCompatActivity() {

    /**
     * Activity 创建回调：初始化 UI，并处理保存/取消逻辑。
     *
     * @param savedInstanceState 输入：Activity 重建时系统提供的状态（可为空）。
     * @return 输出：无返回值。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResult(RESULT_CANCELED)
        setContentView(R.layout.activity_text_widget_configure)

        val appWidgetId = getAppWidgetIdFromIntent(intent)
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        val input = findViewById<EditText>(R.id.text_widget_input)
        input.setText(WidgetPrefs.loadText(this, appWidgetId))

        findViewById<Button>(R.id.text_widget_cancel).setOnClickListener {
            finish()
        }

        findViewById<Button>(R.id.text_widget_save).setOnClickListener {
            val text = input.text?.toString()?.trim().orEmpty()
            if (text.isBlank()) {
                Toast.makeText(this, R.string.text_widget_config_empty, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            WidgetPrefs.saveText(this, appWidgetId, text)
            updateWidgetNow(appWidgetId)
            finishWithOk(appWidgetId)
        }
    }

    /**
     * 从 Intent 中提取 appWidgetId。
     *
     * @param intent 输入：启动配置页的 Intent。
     * @return 输出：解析出的 appWidgetId；若无则返回 INVALID_APPWIDGET_ID。
     */
    private fun getAppWidgetIdFromIntent(intent: Intent?): Int {
        return intent?.extras?.getInt(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID,
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID
    }

    /**
     * 立即刷新指定的小组件实例，让配置结果立刻可见。
     *
     * @param appWidgetId 输入：要刷新的小组件实例 id。
     * @return 输出：无返回值。
     */
    private fun updateWidgetNow(appWidgetId: Int) {
        val manager = AppWidgetManager.getInstance(this)
        val provider = TextWidgetProvider()
        provider.onUpdate(this, manager, intArrayOf(appWidgetId))
    }

    /**
     * 配置完成后，以 RESULT_OK 结束并回传 appWidgetId 给系统桌面。
     *
     * @param appWidgetId 输入：配置完成的小组件实例 id。
     * @return 输出：无返回值。
     */
    private fun finishWithOk(appWidgetId: Int) {
        val resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_OK, resultValue)
        finish()
    }
}

