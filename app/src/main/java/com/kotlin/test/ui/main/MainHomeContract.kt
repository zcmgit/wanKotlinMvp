package com.kotlin.test.ui.main

import com.kotlin.test.base.network.BaseView

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class MainHomeContract{
    interface View : BaseView{

        fun logoutSuccess(msg: String)
        fun logoutFail(msg: String)
    }
    interface  Presenter{
        fun logout()
    }
}
