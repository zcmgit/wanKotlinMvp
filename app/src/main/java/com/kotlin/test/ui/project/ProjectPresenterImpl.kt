package com.kotlin.test.ui.home

import com.kotlin.test.base.api.Api
import com.kotlin.test.base.network.BasePresenter
import com.kotlin.test.base.network.observer.BaseObserver
import com.kotlin.test.base.network.request.RequestManager
import com.kotlin.test.base.network.request.RetrofitManager
import com.kotlin.test.bean.ProjectBean
import com.kotlin.test.bean.article.ArticleBean

/**
 * @author zcm
 * @create 2018/11/19
 * @Describe
 */
class ProjectPresenterImpl (view : ProjectContract.View) : BasePresenter<ProjectContract.View>(view),ProjectContract.Presenter{
    override fun getProjectInfo() {
        RequestManager.execute(this, RetrofitManager.create(Api :: class.java).getProjectInfo(),
                object  : BaseObserver<List<ProjectBean>>(){
                    override fun onSuccess(t: List<ProjectBean>) {
                        view.getProjectSuccess(t)
                    }

                    override fun onFail(code: Int, msg: String) {
                        view.getProjectFail(msg)
                    }
                })
    }
}