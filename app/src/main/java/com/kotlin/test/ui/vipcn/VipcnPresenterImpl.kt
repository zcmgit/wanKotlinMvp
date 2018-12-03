package com.kotlin.test.ui.vipcn

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.vipcn.VipcnBean

/**
 * @author zcm
 * @create 2018/11/30
 * @Describe
 */
class VipcnPresenterImpl(view: VipcnContract.View) : BasePresenter<VipcnContract.View>(view), VipcnContract.Presenter{
    override fun getVipcnInfos() {
        RequestManager.execute(this, RetrofitManager.create(Api :: class.java).getVipcnInfo(),
                object : BaseObserver<List<VipcnBean>>() {
                    override fun onSuccess(t: List<VipcnBean>) {
                        view.getVipcnSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getVipcnFail(msg)
                    }
                })
    }

}