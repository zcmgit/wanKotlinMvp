package com.kotlin.test.ui.tixi

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.article.HomeArticleBean

/**
 * @author zcm
 * @create 2018/11/22
 * @Describe
 */
class SystemArticlePresenter (view: SystemArticleContract.View) : BasePresenter<SystemArticleContract.View>(view), SystemArticleContract.Present{
    override fun getSystemArticleInfo(pageNum : Int ,cid : Int) {
        RequestManager.execute(this, RetrofitManager.create(Api :: class.java).getSystemArticle(pageNum, cid),
                object  : BaseObserver<HomeArticleBean>(){
                    override fun onSuccess(t: HomeArticleBean) {
                        view.getSystemArticleSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getSystemArticleFail(msg)
                    }
                })
    }
}