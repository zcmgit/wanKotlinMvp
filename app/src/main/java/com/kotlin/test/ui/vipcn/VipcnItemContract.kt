package com.kotlin.test.ui.vipcn

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.vipcn.VipcnItemBean

/**
 * @author zcm
 * @create 2018/11/30
 * @Describe
 */
class VipcnItemContract {
    interface View : BaseView {
        fun getVipcnInfoByIdSuccess(infos: VipcnItemBean)
        fun getVipcnInfoByIdFail(msg: String)

        fun setCollectSuccess(msg: String)
        fun setCollectFail(msg: String)

        fun setUnCollectSuccess(msg: String)
        fun setUnCollectFail(msg: String)

        fun searchVipcnSuccess(infos: VipcnItemBean)
        fun searchVipcnFail(msg: String)
    }

    interface Presenter {
        fun getVipcnInfoById(id: Int, pageNum: Int)

        fun setCollect(id: Int)
        fun setUnCollect(id: Int)

        fun searchVipcnInfo(id: Int, pageNum: Int, key: String)
    }
}