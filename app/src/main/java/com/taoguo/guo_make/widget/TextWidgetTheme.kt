package com.taoguo.guo_make.widget

import androidx.annotation.DrawableRes
import androidx.annotation.ColorInt
import com.taoguo.guo_make.R

/**
 * 文本小组件主题：用于控制背景与文字颜色。
 *
 * @return 输出：无返回值。
 */
enum class TextWidgetTheme(
    val id: String,
    @DrawableRes val backgroundRes: Int,
    @ColorInt val textColor: Int,
) {
    /**
     * 浅色干净风格：浅灰卡片 + 深色字。
     */
    Light(
        id = "light",
        backgroundRes = R.drawable.widget_theme_light,
        textColor = 0xFF1F1F1F.toInt(),
    ),

    /**
     * 暖金暗色风格：深色渐变 + 浅色字。
     */
    DarkGold(
        id = "dark_gold",
        backgroundRes = R.drawable.widget_theme_dark_gold,
        textColor = 0xFFF5F5F5.toInt(),
    ),

    /**
     * 冷色蓝紫风格：浅色偏蓝紫渐变 + 深色字。
     */
    CoolBlue(
        id = "cool_blue",
        backgroundRes = R.drawable.widget_theme_cool_blue,
        textColor = 0xFF1F1F1F.toInt(),
    ),

    /**
     * 纯黑极简：纯黑背景 + 纯白字。
     */
    PureBlack(
        id = "pure_black",
        backgroundRes = R.drawable.widget_theme_pure_black,
        textColor = 0xFFFFFFFF.toInt(),
    ),

    /**
     * 薄荷清爽：浅绿渐变 + 深色字。
     */
    Mint(
        id = "mint",
        backgroundRes = R.drawable.widget_theme_mint,
        textColor = 0xFF12302B.toInt(),
    ),

    /**
     * 日落暖色：橙粉渐变 + 深色字。
     */
    Sunset(
        id = "sunset",
        backgroundRes = R.drawable.widget_theme_sunset,
        textColor = 0xFF2B1206.toInt(),
    ),

    /**
     * 薰衣草：淡紫渐变 + 深色字。
     */
    Lavender(
        id = "lavender",
        backgroundRes = R.drawable.widget_theme_lavender,
        textColor = 0xFF221A2C.toInt(),
    ),

    /**
     * 森林绿：深绿渐变 + 浅色字。
     */
    Forest(
        id = "forest",
        backgroundRes = R.drawable.widget_theme_forest,
        textColor = 0xFFF2FFF9.toInt(),
    ),

    /**
     * 玫瑰粉：淡粉渐变 + 深色字。
     */
    Rose(
        id = "rose",
        backgroundRes = R.drawable.widget_theme_rose,
        textColor = 0xFF2B0C13.toInt(),
    ),

    /**
     * 石墨灰：深灰渐变 + 浅色字。
     */
    Graphite(
        id = "graphite",
        backgroundRes = R.drawable.widget_theme_graphite,
        textColor = 0xFFF5F5F5.toInt(),
    ),
    ;

    companion object {
        /**
         * 根据 id 获取主题。
         *
         * @param id 输入：主题 id。
         * @return 输出：对应主题；若找不到则返回默认主题 Light。
         */
        fun fromId(id: String?): TextWidgetTheme {
            return entries.firstOrNull { it.id == id } ?: Light
        }
    }
}

