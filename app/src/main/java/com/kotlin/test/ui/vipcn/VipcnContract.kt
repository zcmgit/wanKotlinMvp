package com.kotlin.test.ui.vipcn

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.vipcn.VipcnBean

/**
 * @author zcm
 * @create 2018/11/30
 * @Describe
 */
class VipcnContract{
    interface View : BaseView{
        fun getVipcnSuccess(infos: List<VipcnBean>)
        fun getVipcnFail(msg: String)
    }

    interface Presenter{
        fun getVipcnInfos()
    }
}