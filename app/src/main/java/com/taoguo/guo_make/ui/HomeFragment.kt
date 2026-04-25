package com.taoguo.guo_make.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.taoguo.guo_make.R

/**
 * 首页：展示说明与权限设置入口。
 *
 * @return 输出：无返回值。
 */
class HomeFragment : Fragment() {

    /**
     * 创建并返回 Fragment 的视图。
     *
     * @param inflater 输入：布局 inflater。
     * @param container 输入：父容器（可为空）。
     * @param savedInstanceState 输入：状态（可为空）。
     * @return 输出：Fragment 根视图。
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    /**
     * 视图创建完成回调：绑定按钮事件。
     *
     * @param view 输入：根视图。
     * @param savedInstanceState 输入：状态（可为空）。
     * @return 输出：无返回值。
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.open_app_settings).setOnClickListener {
            openAppDetailsSettings()
        }
    }

    /**
     * 打开系统“应用详情”设置页，便于用户手动开启权限（如 HyperOS 的「桌面快捷方式」）。
     *
     * @return 输出：无返回值。
     */
    private fun openAppDetailsSettings() {
        val ctx = requireContext()
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.parse("package:${ctx.packageName}")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }
}

