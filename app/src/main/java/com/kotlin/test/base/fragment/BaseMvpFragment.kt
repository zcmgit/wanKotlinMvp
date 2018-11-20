package com.kotlin.test.base.fragment

import android.os.Bundle

import com.kotlin.test.base.network.BasePresenter

/**
 * Author: shehuan
 * Date: 2018/11/12 9:13
 * Description:
 */
abstract class BaseMvpFragment<P : BasePresenter<*>> : BaseFragment() {
    lateinit var presenter: P

    abstract fun initPresenter(): P
    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = initPresenter()
        super.onCreate(savedInstanceState)
    }



    override fun onDestroy() {
        super.onDestroy()
    }
}
