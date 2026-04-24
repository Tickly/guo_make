package com.taoguo.guo_make

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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
    }
}

