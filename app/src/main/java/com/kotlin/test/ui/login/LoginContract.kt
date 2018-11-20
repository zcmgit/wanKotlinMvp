package com.kotlin.test.ui.login

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.LoginBean

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
class LoginContract{
    interface View : BaseView{
        fun loginSuccess(t: LoginBean)
        fun loginFail(s : String)
    }
    interface Presenter{
        fun login(name : String , pwd : String)
    }
}