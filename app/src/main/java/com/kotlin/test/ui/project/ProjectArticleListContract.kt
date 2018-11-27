package com.kotlin.test.ui.project

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.article.ArticleBean

/**
 * @author zcm
 * @create 2018/11/27
 * @Describe
 */
class ProjectArticleListContract {
    interface View : BaseView {
        fun getProjectItemSuccess(articleList: ArticleBean)
        fun getProjectItemFail(msg: String)
    }

    interface Presenter {
        fun getProjectItem(pageNum: Int, cid: Int)
    }
}