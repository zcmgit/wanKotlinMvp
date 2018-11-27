package com.kotlin.test.ui.home

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.HomeBannerBean
import com.kotlin.test.bean.article.ArticleBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class HomeContract {
    interface View : BaseView {
        fun getBannerSuccess(bannerBean: List<HomeBannerBean>)
        fun getBannerFail(info: String)

        fun getArticleSuccess(articles: ArticleBean)
        fun getArticleFail(info: String)

        fun setCollectSuccess(msg: String)
        fun setCollectFail(msg: String)

        fun setUnCollectSuccess(msg: String)
        fun setUnCollectFail(msg: String)
    }

    interface Presenter {
        fun getBanner()
        fun getArticle(pageNum: Int)
        fun setCollect(id: Int)
        fun setUnCollect(id: Int)
    }
}