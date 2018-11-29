package com.kotlin.test.ui.collect

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.article.ArticleBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class CollectContract {
    interface View : BaseView {
        fun getCollectSuccess(collectInfo: ArticleBean)
        fun getCollectFail(msg: String)

        fun setUnCollectSuccess(msg: String)
        fun setUnCollectFail(msg: String)
    }

    interface Presenter {
        fun getCollectInfos(pageNum: Int)
        fun setUnCollect(id: Int,organId: Int)
    }
}