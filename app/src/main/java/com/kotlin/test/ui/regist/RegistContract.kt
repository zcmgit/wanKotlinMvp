package com.kotlin.test.ui.regist

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.RegisterBean

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
class RegistContract{
    interface View :BaseView {
        fun registSuccess(bean: RegisterBean)
        fun registFail(info : String)
    }

    interface Persenter{
        fun regist(name : String ,reName :String,pwd : String)
    }
}