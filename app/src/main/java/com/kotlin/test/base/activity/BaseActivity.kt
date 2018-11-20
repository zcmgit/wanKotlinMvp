package com.kotlin.test.base.activity

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


abstract class BaseActivity : AppCompatActivity() {
    protected var TAG = this.javaClass.simpleName

    lateinit var context: Context

    /**
     * 初始化布局文件id
     */
    @LayoutRes
    protected abstract fun initContentViewId(): Int

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    /**
     * 是否初始化EventBus
     */
    protected fun initEventBus(): Boolean {
        return false
    }

    /**
     * 初始化组件
     */
    protected abstract fun initView()

    /**
     * 页面初始化数据请求、内容加载
     */
    protected abstract fun initLoad()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initContentViewId())
        context = this

        initData()
        initView()
        initLoad()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
