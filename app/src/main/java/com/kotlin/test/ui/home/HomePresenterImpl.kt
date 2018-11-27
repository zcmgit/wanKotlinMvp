package com.kotlin.test.ui.home

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.HomeBannerBean
import com.kotlin.test.bean.article.ArticleBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class HomePresenterImpl(view: HomeContract.View) : BasePresenter<HomeContract.View>(view), HomeContract.Presenter {
    override fun getBanner() {
        RequestManager.execute(this, RetrofitManager.create(Api::class.java).getHomeBanner(),
                object : BaseObserver<List<HomeBannerBean>>() {
                    override fun onSuccess(t: List<HomeBannerBean>) {
                        view.getBannerSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getBannerFail(msg)
                    }
                })
    }

    override fun getArticle(pageNum: Int) {
        RequestManager.execute(this, RetrofitManager.create(Api::class.java).getHomeArticle(pageNum),
                object : BaseObserver<ArticleBean>() {
                    override fun onSuccess(t: ArticleBean) {
                        view.getArticleSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getArticleFail(msg)
                    }
                })
    }

    override fun setCollect(id: Int) {
        RequestManager.execute(this, RetrofitManager.create(Api::class.java).setCollect(id),
                object : BaseObserver<String>() {
                    override fun onSuccess(t: String) {
                        view.setCollectSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.setCollectFail(msg)
                    }

                })
    }

    override fun setUnCollect(id: Int) {
        RequestManager.execute(this, RetrofitManager.create(Api::class.java).setUnCollect(id),
                object : BaseObserver<String>() {
                    override fun onSuccess(t: String) {
                        view.setUnCollectSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.setUnCollectFail(msg)
                    }

                })
    }
}