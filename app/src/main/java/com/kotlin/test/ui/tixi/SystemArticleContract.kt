package com.kotlin.test.ui.tixi

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.article.ArticleBean

/**
 * @author zcm
 * @create 2018/11/22
 * @Describe
 */
class SystemArticleContract{
    interface View : BaseView{
        fun getSystemArticleSuccess(bean: ArticleBean)
        fun getSystemArticleFail(msg : String)
    }

    interface Present{
        fun getSystemArticleInfo(pageNum : Int ,cid : Int)
    }
}