package com.kotlin.test.ui.tixi

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.LoginBean
import com.kotlin.test.bean.article.HomeArticleBean
import com.kotlin.test.bean.system.SystemInfoBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class SystemPresenterImpl (view: SystemContract.View) : BasePresenter<SystemContract.View>(view), SystemContract.Presenter{
    override fun getSystemInfo() {
        RequestManager.execute(this, RetrofitManager.create(Api :: class.java).getSystemInfo(),
                object : BaseObserver<List<SystemInfoBean>>() {
                    override fun onSuccess(t: List<SystemInfoBean>) {
                        view.getSystemSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getSystemFail(msg)
                    }
                })
    }
}