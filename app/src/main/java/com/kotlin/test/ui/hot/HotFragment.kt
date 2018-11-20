package com.kotlin.test.ui.home

import com.kotlin.test.R
import com.kotlin.test.base.fragment.BaseMvpFragment

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class HotFragment : BaseMvpFragment<HotPresenterImpl>(),HotContract.View{

    companion object {
        fun newInstance() = HotFragment()
    }

    override fun initPresenter(): HotPresenterImpl {
        return HotPresenterImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.fragment_hot
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initLoad() {
    }

}