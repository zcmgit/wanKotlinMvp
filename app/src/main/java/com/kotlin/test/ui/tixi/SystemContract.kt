package com.kotlin.test.ui.tixi

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.article.HomeArticleBean
import com.kotlin.test.bean.system.SystemInfoBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class SystemContract {
    interface View : BaseView {
        fun getSystemSuccess(bean: List<SystemInfoBean>)
        fun getSystemFail(msg: String)

    }

    interface Presenter {
        fun getSystemInfo()
    }
}