package com.kotlin.test.ui.main

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.article.ArticleBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class MainHomePresenterImpl(view: MainHomeContract.View) : BasePresenter<MainHomeContract.View>(view), MainHomeContract.Presenter {
    override fun logout() {
        RequestManager.execute(this, RetrofitManager.create(Api::class.java).logout(),
                object : BaseObserver<String>() {
                    override fun onSuccess(t: String) {
                        view.logoutSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.logoutFail(msg)
                    }
                })
    }

}