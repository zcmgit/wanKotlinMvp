package com.kotlin.test.ui.tixi

import com.kotlin.test.R
import com.kotlin.test.base.fragment.BaseMvpFragment

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class SystemFragment : BaseMvpFragment<SystemPresenterImpl>(),SystemContract.View{

    companion object {
        fun newInstance() = SystemFragment()
    }

    override fun initPresenter(): SystemPresenterImpl {
        return SystemPresenterImpl(this)
    }

    override fun initContentViewId(): Int {
        return R.layout.fragment_system
    }

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initLoad() {
    }

}