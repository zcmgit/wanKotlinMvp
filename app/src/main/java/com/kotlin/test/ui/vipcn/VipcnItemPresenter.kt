package com.kotlin.test.ui.vipcn

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.BaseView
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.vipcn.VipcnBean
import com.kotlin.test.bean.vipcn.VipcnItemBean

/**
 * @author zcm
 * @create 2018/11/30
 * @Describe
 */
class VipcnItemPresenter(view: VipcnItemContract.View) : BasePresenter<VipcnItemContract.View>(view), VipcnItemContract.Presenter {
    override fun searchVipcnInfo(id: Int, pageNum: Int, key: String) {
        RequestManager.execute(this, RetrofitManager.create(Api::class.java).searchVipcnInfo(id,pageNum,key),
                object : BaseObserver<VipcnItemBean>() {
                    override fun onSuccess(t: VipcnItemBean) {
                        view.searchVipcnSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.searchVipcnFail(msg)
                    }
                })
    }

    override fun getVipcnInfoById(id: Int, pageNum: Int) {
        RequestManager.execute(this, RetrofitManager.create(Api::class.java).getVipcnInfoById(id,pageNum),
                object : BaseObserver<VipcnItemBean>() {
                    override fun onSuccess(t: VipcnItemBean) {
                        view.getVipcnInfoByIdSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getVipcnInfoByIdFail(msg)
                    }
                })
    }

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
}