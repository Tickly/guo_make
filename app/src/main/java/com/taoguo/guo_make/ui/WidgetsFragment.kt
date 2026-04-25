package com.taoguo.guo_make.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.taoguo.guo_make.R
import com.taoguo.guo_make.widget.DateWidgetProvider
import com.taoguo.guo_make.widget.PinWidgetHelper
import com.taoguo.guo_make.widget.TextWidgetProvider

/**
 * 小部件页：以卡片形式展示可添加的小部件。
 *
 * @return 输出：无返回值。
 */
class WidgetsFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_widgets, container, false)
    }

    /**
     * 视图创建完成回调：绑定两张卡片的点击事件。
     *
     * @param view 输入：根视图。
     * @param savedInstanceState 输入：状态（可为空）。
     * @return 输出：无返回值。
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.card_add_date_widget).setOnClickListener {
            pinWidget(DateWidgetProvider::class.java)
        }

        view.findViewById<View>(R.id.card_add_text_widget).setOnClickListener {
            pinWidget(TextWidgetProvider::class.java)
        }
    }

    /**
     * 尝试一键添加指定 Provider 的小部件，并给出 Toast 反馈。
     *
     * @param providerClass 输入：AppWidgetProvider 的 class。
     * @return 输出：无返回值。
     */
    private fun pinWidget(providerClass: Class<out Any>) {
        val ctx = requireContext()
        val result = PinWidgetHelper.requestPin(ctx, providerClass)
        val msgRes = when (result) {
            PinWidgetHelper.PinResult.NotSupported -> R.string.add_widget_not_supported
            PinWidgetHelper.PinResult.RequestSent -> R.string.add_widget_request_sent
            PinWidgetHelper.PinResult.Failed -> R.string.add_widget_request_failed
        }
        Toast.makeText(ctx, msgRes, Toast.LENGTH_LONG).show()
    }
}

