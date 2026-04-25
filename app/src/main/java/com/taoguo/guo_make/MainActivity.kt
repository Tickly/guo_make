package com.taoguo.guo_make

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2
import com.taoguo.guo_make.ui.MainTabsAdapter

/**
 * 应用主界面 Activity。
 *
 * @param savedInstanceState 输入：Activity 重建时系统提供的状态（可为空）。
 * @return 输出：无返回值。
 */
class MainActivity : AppCompatActivity() {
    /**
     * Activity 创建回调。
     *
     * @param savedInstanceState 输入：Activity 重建时系统提供的状态（可为空）。
     * @return 输出：无返回值。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pager = findViewById<ViewPager2>(R.id.main_pager)
        val tabs = findViewById<TabLayout>(R.id.main_tabs)
        pager.adapter = MainTabsAdapter(this)

        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tab_home)
                1 -> getString(R.string.tab_widgets)
                else -> getString(R.string.tab_home)
            }
        }.attach()
    }
}

