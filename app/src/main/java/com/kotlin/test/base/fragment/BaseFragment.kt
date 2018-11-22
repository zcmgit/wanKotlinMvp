package com.kotlin.test.base.fragment

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


abstract class BaseFragment : Fragment() {
    protected var TAG = this.javaClass.simpleName


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

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    /**
     * 返回视图view
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(initContentViewId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initLoad()
    }


    override fun onDestroy() {
        super.onDestroy()
    }


}
