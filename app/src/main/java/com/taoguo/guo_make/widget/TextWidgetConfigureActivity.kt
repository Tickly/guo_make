package com.taoguo.guo_make.widget

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.RadioGroup
import android.widget.TextView
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
    private lateinit var previewBg: FrameLayout
    private lateinit var previewText: TextView
    private lateinit var input: EditText
    private lateinit var themeGroup: RadioGroup
    private var selectedTheme: TextWidgetTheme = TextWidgetTheme.Light

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

        input = findViewById(R.id.text_widget_input)
        previewBg = findViewById(R.id.text_widget_preview_bg)
        previewText = findViewById(R.id.text_widget_preview_text)
        themeGroup = findViewById(R.id.text_widget_theme_group)

        input.setText(WidgetPrefs.loadText(this, appWidgetId))
        selectedTheme = TextWidgetTheme.fromId(WidgetPrefs.loadThemeId(this, appWidgetId))
        applyThemeToUi(selectedTheme)
        applyTextToPreview(input.text?.toString().orEmpty())

        themeGroup.check(themeIdToRadioId(selectedTheme))
        themeGroup.setOnCheckedChangeListener { _, checkedId ->
            selectedTheme = when (checkedId) {
                R.id.theme_dark_gold -> TextWidgetTheme.DarkGold
                R.id.theme_cool_blue -> TextWidgetTheme.CoolBlue
                R.id.theme_pure_black -> TextWidgetTheme.PureBlack
                R.id.theme_mint -> TextWidgetTheme.Mint
                R.id.theme_sunset -> TextWidgetTheme.Sunset
                R.id.theme_lavender -> TextWidgetTheme.Lavender
                R.id.theme_forest -> TextWidgetTheme.Forest
                R.id.theme_rose -> TextWidgetTheme.Rose
                R.id.theme_graphite -> TextWidgetTheme.Graphite
                else -> TextWidgetTheme.Light
            }
            applyThemeToUi(selectedTheme)
        }

        input.addTextChangedListener(SimpleTextWatcher { s ->
            applyTextToPreview(s)
        })

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
            WidgetPrefs.saveTheme(this, appWidgetId, selectedTheme.id)
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
     * 将主题应用到配置页的预览区域。
     *
     * @param theme 输入：选择的主题。
     * @return 输出：无返回值。
     */
    private fun applyThemeToUi(theme: TextWidgetTheme) {
        previewBg.setBackgroundResource(theme.backgroundRes)
        previewText.setTextColor(theme.textColor)
        previewText.setBackgroundResource(theme.chipRes)
    }

    /**
     * 将输入文本同步到预览区域（空文本时使用默认示例）。
     *
     * @param text 输入：当前输入框内容。
     * @return 输出：无返回值。
     */
    private fun applyTextToPreview(text: String) {
        val v = text.trim().ifBlank { getString(R.string.text_widget_default_text) }
        previewText.text = v
    }

    /**
     * 将主题映射到 RadioButton 的 id。
     *
     * @param theme 输入：主题。
     * @return 输出：RadioButton id。
     */
    private fun themeIdToRadioId(theme: TextWidgetTheme): Int {
        return when (theme) {
            TextWidgetTheme.Light -> R.id.theme_light
            TextWidgetTheme.DarkGold -> R.id.theme_dark_gold
            TextWidgetTheme.CoolBlue -> R.id.theme_cool_blue
            TextWidgetTheme.PureBlack -> R.id.theme_pure_black
            TextWidgetTheme.Mint -> R.id.theme_mint
            TextWidgetTheme.Sunset -> R.id.theme_sunset
            TextWidgetTheme.Lavender -> R.id.theme_lavender
            TextWidgetTheme.Forest -> R.id.theme_forest
            TextWidgetTheme.Rose -> R.id.theme_rose
            TextWidgetTheme.Graphite -> R.id.theme_graphite
        }
    }

    /**
     * 极简文本监听器：只关心 onTextChanged。
     *
     * @return 输出：无返回值。
     */
    private class SimpleTextWatcher(
        private val onTextChanged: (String) -> Unit,
    ) : android.text.TextWatcher {
        /**
         * @param s 输入：无用。
         * @param start 输入：无用。
         * @param count 输入：无用。
         * @param after 输入：无用。
         * @return 输出：无返回值。
         */
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        /**
         * @param s 输入：文本内容。
         * @param start 输入：无用。
         * @param before 输入：无用。
         * @param count 输入：无用。
         * @return 输出：无返回值。
         */
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged(s?.toString().orEmpty())
        }

        /**
         * @param s 输入：无用。
         * @return 输出：无返回值。
         */
        override fun afterTextChanged(s: android.text.Editable?) = Unit
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

