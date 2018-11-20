package com.kotlin.test.ui.home

import com.kotlin.test.R
import com.kotlin.test.base.fragment.BaseMvpFragment

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class HomeFragment : BaseMvpFragment<HomePresenterImpl>(),HomeContract.View{

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun initPresenter(): HomePresenterImpl {
        return HomePresenterImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.fragment_home
    }

    override fun initData() {
    }

    override fun initView() {

    }

    override fun initLoad() {
    }

}