package com.taoguo.guo_make.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * 主界面 Tab 页适配器：提供“首页 / 小部件”两个页面。
 *
 * @param activity 输入：FragmentActivity 宿主。
 * @return 输出：无返回值。
 */
class MainTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    /**
     * 返回页面数量。
     *
     * @return 输出：页面数量。
     */
    override fun getItemCount(): Int = 2

    /**
     * 根据位置创建 Fragment。
     *
     * @param position 输入：页面索引。
     * @return 输出：对应页面 Fragment。
     */
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> WidgetsFragment()
            else -> HomeFragment()
        }
    }
}

