package com.kotlin.test.ui.collect

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.CollectBean
import com.kotlin.test.bean.article.ArticleBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class CollectPresentImpl(view: CollectContract.View) : BasePresenter<CollectContract.View>(view), CollectContract.Presenter {
    override fun getCollectInfos(pageNum : Int) {
        RequestManager.execute(this, RetrofitManager.create(Api::class.java).getCollectInfos(pageNum),
                object : BaseObserver<ArticleBean>() {
                    override fun onSuccess(t: ArticleBean) {
                        view.getCollectSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getCollectFail(msg)
                    }
                })
    }
}