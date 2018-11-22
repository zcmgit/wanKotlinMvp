package com.kotlin.test.ui.home

import com.kotlin.test.base.network.BaseView
import com.kotlin.test.bean.HomeBannerBean
import com.kotlin.test.bean.article.HomeArticleBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class HomeContract{
    interface View : BaseView{
        fun getBannerSuccess(bannerBean : List<HomeBannerBean>)
        fun getBannerFail(info : String)

        fun getArticleSuccess(articles : HomeArticleBean)
        fun getArticleFail(info : String)
    }

    interface Presenter{
        fun getBanner()

        fun getArticle(pageNum : Int)
    }
}