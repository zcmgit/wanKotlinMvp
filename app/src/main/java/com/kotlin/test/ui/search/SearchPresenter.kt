package com.kotlin.test.ui.search

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.HotBean
import com.kotlin.test.bean.WebBean
import com.kotlin.test.bean.article.ArticleBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class SearchPresenter (view: SearchContract.View) : BasePresenter<SearchContract.View>(view),SearchContract.Presenter{
    override fun searchInfoByKey(pageNum: Int, key: String) {
        RequestManager.execute(this, RetrofitManager.create(Api :: class.java).searchInfoByKey(pageNum, key),
                object : BaseObserver<ArticleBean>(){
                    override fun onSuccess(t: ArticleBean) {
                        view.searchInfoSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.searchInfoFail(msg)
                    }

                })
    }

    override fun getHotInfos() {
        RequestManager.execute(this, RetrofitManager.create(Api :: class.java).getHotInfos(),
                object : BaseObserver<List<HotBean>>(){
                    override fun onSuccess(t: List<HotBean>) {
                        view.getHotInfosSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getHotInfosFail(msg)
                    }

                })
    }

    override fun getFriendWebInfos() {
        RequestManager.execute(this, RetrofitManager.create(Api :: class.java).getFriendWebInfos(),
                object : BaseObserver<List<WebBean>>(){
                    override fun onSuccess(t: List<WebBean>) {
                        view.getFriendWebSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getFriendWebFail(msg)
                    }

                })
    }

}