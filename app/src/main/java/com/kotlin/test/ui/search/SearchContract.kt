package com.kotlin.test.ui.search

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.HotBean
import com.kotlin.test.bean.WebBean
import com.kotlin.test.bean.article.ArticleBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class SearchContract {
    interface View : BaseView {
        fun getHotInfosSuccess(hots: List<HotBean>)
        fun getHotInfosFail(msg: String)

        fun getFriendWebSuccess(webs: List<WebBean>)
        fun getFriendWebFail(msg: String)

        fun searchInfoSuccess(articles: ArticleBean)
        fun searchInfoFail(msg: String)
    }

    interface Presenter{
        fun getHotInfos()
        fun getFriendWebInfos()
        fun searchInfoByKey(pageNum: Int,key: String)
    }
}
