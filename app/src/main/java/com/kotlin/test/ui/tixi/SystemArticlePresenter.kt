package com.kotlin.test.ui.tixi

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.article.ArticleBean

/**
 * @author zcm
 * @create 2018/11/22
 * @Describe
 */
class SystemArticlePresenter (view: SystemArticleContract.View) : BasePresenter<SystemArticleContract.View>(view), SystemArticleContract.Present{
    override fun setUnCollect(id: Int) {
        RequestManager.execute(this,RetrofitManager.create(Api::class.java).setUnCollect(id),
                object : BaseObserver<String>(){
                    override fun onSuccess(t: String) {
                        view.setUnCollectSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.setUnCollectFail(msg)
                    }

                })
    }

    override fun setCollect(id: Int) {
        RequestManager.execute(this,RetrofitManager.create(Api::class.java).setCollect(id),
                object : BaseObserver<String>(){
                    override fun onSuccess(t: String) {
                        view.setCollectSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.setCollectFail(msg)
                    }

                })
    }

    override fun getSystemArticleInfo(pageNum : Int ,cid : Int) {
        RequestManager.execute(this, RetrofitManager.create(Api :: class.java).getSystemArticle(pageNum, cid),
                object  : BaseObserver<ArticleBean>(){
                    override fun onSuccess(t: ArticleBean) {
                        view.getSystemArticleSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getSystemArticleFail(msg)
                    }
                })
    }
}